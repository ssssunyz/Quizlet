package com.bfs.quizlet.controller;

import com.bfs.quizlet.domain.Choice;
import com.bfs.quizlet.domain.Question;
import com.bfs.quizlet.domain.QuizQuestionChoice;
import com.bfs.quizlet.domain.User;
import com.bfs.quizlet.service.ChoiceService;
import com.bfs.quizlet.service.QuestionService;
import com.bfs.quizlet.service.QuizQuestionChoiceService;
import com.bfs.quizlet.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

@Controller
public class QuizController {

    private QuestionService questionService;
    private ChoiceService choiceService;
    private QuizService quizService;
    private QuizQuestionChoiceService quizQuestionChoiceService;

    @Autowired
    public QuizController(QuestionService questionService, ChoiceService choiceService,
                          QuizService quizService, QuizQuestionChoiceService quizQuestionChoiceService) {
        this.questionService = questionService;
        this.choiceService = choiceService;
        this.quizService = quizService;
        this.quizQuestionChoiceService = quizQuestionChoiceService;
    }

    @GetMapping("/quiz/{category_id}")
    public String getQuiz(Model model, @PathVariable int category_id) {
        // get questions
        List<Question> questions = questionService.getFiveQuestionByCategory(category_id);
        Map<Question, List<Choice>> qcMap = new HashMap<>();

        // get choices for each question
        for (Question q : questions)
        {
            List<Choice> choices = choiceService.getChoicesByQuestion(q.getQuestion_id());
            qcMap.put(q, choices);
        }

        model.addAttribute("qcMap", qcMap);  // 在quiz.jsp里即可通过qcMap来access这些question和choice
        return "quiz";
    }

    // user has submitted quiz
    @PostMapping("/quiz")
    public String submitQuiz(@RequestParam Map<String, String> questionChoiceMap,
                             HttpSession session) {
        // record end time
        session.setAttribute("end_time", System.currentTimeMillis());

        Map<Integer, Integer> qcMap = new HashMap<>();  // question id -> selected choice id
        questionChoiceMap.forEach((question, choice) -> {
            if (question.startsWith("question")) {
                Integer questionId = Integer.parseInt(question.substring("question".length()));
                qcMap.put(questionId, Integer.parseInt(choice));
            }
        });

        session.setAttribute("qcMap", qcMap);
        // System.out.println("In QuizController: SubmitQuiz: " + session.getAttribute("qcMap"));
        return "redirect:/quiz-result";
    }

    @GetMapping("/quiz-result")
    public String getQuizResult(Model model, HttpSession session) {

        // lists to be added to model attribute
        List<String> questionDescriptions = new ArrayList<>();
        List<List<String>> allOptions = new ArrayList<>();
        List<String> selectedChoiceDescriptions = new ArrayList<>();
        List<String> results = new ArrayList<>();
        List<String> correctAnswers = new ArrayList<>();

        int categoryId = -1;  // to be updated later
        int correctCount = 0;
        StringBuilder detail = new StringBuilder();

        Map<Integer, Integer> qcMap = (Map<Integer, Integer>) session.getAttribute("qcMap");
        // key: question_id, value: selected_choice_id
        for (Map.Entry<Integer, Integer> entry : qcMap.entrySet())
        {
            int questionId = entry.getKey();
            int selectedChoiceId = entry.getValue();
            Optional<Choice> possibleChoice = choiceService.getChoiceById(selectedChoiceId);

            // quizQuestionChoice: 本来想用这个存detail的...
            // private int quiz_id;  // 这还没存进db呢 咋get啊...

            if (possibleChoice.isPresent()) {
                // get selected choice
                Choice selectedChoice = possibleChoice.get();
                selectedChoiceDescriptions.add(selectedChoice.getChoice_description());

                // get corresponding question
                Question question = questionService.getQuestionById(questionId);
                questionDescriptions.add(question.getDescription());
                categoryId = question.getCategory_id();
                detail.append("<br>Question: " + question.getDescription() + "<br>");

                // get all choices
                List<Choice> allChoices = choiceService.getChoicesByQuestion(questionId);
                List<String> options = new ArrayList<>();
                for(Choice choice : allChoices)
                {
                    options.add(choice.getChoice_description());
                    detail.append("* " + choice.getChoice_description() + "<br>");
                }
                allOptions.add(options);
                detail.append("The user selected: \"" + selectedChoice.getChoice_description() + "\",<br>");

                // get result
                boolean isCorrect = selectedChoice.is_correct();
                String result = isCorrect? "CORRECT" : "WRONG";
                results.add(result);
                if(isCorrect) correctCount++;
                detail.append("which is " + result + "\n<br>");

                // get correct answer
                int correctChoiceId = question.getCorrectChoiceId();
                Optional<Choice> possibleCorrectChoice = choiceService.getChoiceById(correctChoiceId);
                if (possibleChoice.isPresent()) {
                    Choice correctChoice = possibleCorrectChoice.get();
                    correctAnswers.add(correctChoice.getChoice_description());
                    if (!isCorrect)  // 选错了
                        detail.append("<br>Correct answer: \"" + correctChoice.getChoice_description() + "\".");
                }
            } else {
                model.addAttribute("result", "Invalid choice");
            }
        }

        // store quiz result to database
        User user = (User) session.getAttribute("user");
        Timestamp start_time = new Timestamp((Long) session.getAttribute("start_time"));
        Timestamp end_time = new Timestamp((Long) session.getAttribute("end_time"));
        String quiz_name = "quiz-" + categoryId + "-" + LocalDate.now();
        quizService.storeQuizResult(user.getUser_id(), categoryId, quiz_name, start_time, end_time, correctCount, detail.toString());

        // add attributes
        model.addAttribute("quiz_name", quiz_name);
        model.addAttribute("start_time", start_time);
        model.addAttribute("end_time", end_time);
        model.addAttribute("question_descriptions", questionDescriptions);
        model.addAttribute("options_to_question", allOptions);
        model.addAttribute("selected_choice_descriptions", selectedChoiceDescriptions);
        model.addAttribute("results", results);
        model.addAttribute("correct_answers", correctAnswers);
        model.addAttribute("correct_count", correctCount);

        return "quiz-result";
    }
}
