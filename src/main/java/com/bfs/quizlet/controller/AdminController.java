package com.bfs.quizlet.controller;

import com.bfs.quizlet.domain.*;
import com.bfs.quizlet.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    private final QuizService quizService;
    private final QuestionService questionService;
    private final UserService userService;
    private final ContactService contactService;
    private final FeedbackService feedbackService;

    public AdminController(QuizService quizService, QuestionService questionService, UserService userService,
                           ContactService contactService, FeedbackService feedbackService) {
        this.quizService = quizService;
        this.questionService = questionService;
        this.userService = userService;
        this.contactService = contactService;
        this.feedbackService = feedbackService;
    }

    @GetMapping("/admin-home")
    public String getAdminHome(HttpServletRequest request, Model model) {
        /* getSession(false): retrieves the current session, and if doesn't exist, returns null
           getSession()/getSession(true): if doesn't exist, create one */
        HttpSession session = request.getSession(false);

        // redirect to /login if user has not logged in
        if (session == null) return "redirect:/login";

        User user = (User) session.getAttribute("user");  // 是在LoginController的postLogin里set的:
                                                             // newSession.setAttribute("user", possibleUser.get());
        // is not admin
        if (!user.is_admin()) return "redirect:/home";

        // is admin
        return "admin-home";  // return admin-home.jsp
    }

    // admin has selected which action to take (eg. view all users, view all quiz result, ...)
    @GetMapping("/admin-home/{action}")
    public String selectAction(Model model, @PathVariable int action) {
        // System.out.println("In AdminController: selectAction");
        switch (action)
        {
            case 1: return "redirect:/all-user";
            case 2: return "redirect:/all-quiz-result";
            case 3: return "redirect:/all-feedback";
            case 4: return "redirect:/all-contact-messages";
            case 5: return "redirect:/all-questions";
        }
        return "admin-home";
    }

    @GetMapping("/all-user")
    public String viewAllUser(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("all_users", allUsers);
        return "all-user";  // return all-user.jsp
    }

    @GetMapping("/all-quiz-result")
    public String viewAllQuiz(Model model) {
        List<Quiz> allQuiz = quizService.getAllQuiz();  // sorted by date DESC
        model.addAttribute("all_quiz", allQuiz);

        List<User> correspondingUsers = new ArrayList<>();
        for(Quiz quiz : allQuiz)
        {
            int userId = quiz.getUser_id();
            User user = userService.getUserById(userId);
            correspondingUsers.add(user);
        }
        model.addAttribute("corresponding_users", correspondingUsers);

        return "all-quiz-result";
    }

    // admin clicked on a specific quiz result, should return quiz detail page
    @GetMapping("/all-quiz-result/{quiz_id}")
    public String getQuizDetail(@PathVariable int quiz_id, Model model) {
        System.out.println("In AdminController: getQuizDetail");
        Quiz quiz = quizService.getQuizByQuizId(quiz_id);
        User user = userService.getUserById(quiz.getUser_id());
        model.addAttribute("start_time", quiz.getStart_time());
        model.addAttribute("end_time", quiz.getEnd_time());
        model.addAttribute("score", quiz.getScore());
        model.addAttribute("firstname", user.getFirstname());
        model.addAttribute("lastname", user.getLastname());
        model.addAttribute("detail", quiz.getDetail());
        return "quiz-detail";
    }

    @GetMapping("/all-feedback")
    public String viewAllFeedback(Model model) {
        List<Feedback> allFeedbacks = feedbackService.getAllFeedbacks();
        model.addAttribute("all_feedbacks", allFeedbacks);
        return "all-feedback";
    }

    @GetMapping("/all-questions")
    public String viewAllQuestion(Model model) {
        List<Question> javaCore = questionService.getAllQuestionByCategory(1);
        List<Question> tennis = questionService.getAllQuestionByCategory(2);
        List<Question> funFacts = questionService.getAllQuestionByCategory(3);
        model.addAttribute("java_core", javaCore);
        model.addAttribute("tennis", tennis);
        model.addAttribute("fun_facts", funFacts);
        return "all-question";
    }

    // admin added a question
    @PostMapping("/add-question")
    public String addQuestion(@RequestParam int categoryId, @RequestParam String description) {
        questionService.addQuestion(categoryId, description);
        return "redirect:/success-redirect-home";
    }

    @PostMapping("/toggle-user")
    public String changeUserStatus(@RequestParam(name = "toggleButton") int userId) {
        // all-user.jsp里define了name=toggleButton的button的value是对应的userId
        // System.out.println("In AdminController: updateUser");
        User user = userService.getUserById(userId);
        int changedStatus = user.is_active()?  0 : 1;  // 现在active就让他不active, vice versa
        userService.changeUserStatus(userId, changedStatus);
        return "redirect:/success-redirect-home";
    }

    
    // admin changed status of a question
    @PostMapping("/toggle-question")
    public String changeQuestionStatus(@RequestParam(name = "toggleButton") int questionId) {
        // all-user.jsp里define了name=toggleButton的button的value是对应的questionId
        Question question = questionService.getQuestionById(questionId);
        int changedStatus = question.is_active()?  0 : 1;  // 现在active就让他不active, vice versa
        questionService.changeQuestionStatus(questionId, changedStatus);
        return "redirect:/success-redirect-home";
    }

    // admin click on update a question
    @PostMapping("/update-question")
    public String prepareUpdateQuestion(@RequestParam(name = "updateButton") int questionId, Model model) {
        Question question = questionService.getQuestionById(questionId);
        model.addAttribute("question_id", questionId);
        model.addAttribute("category_id", question.getCategory_id());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("is_active", question.is_active());
        model.addAttribute("correct_choice_id", question.getCorrectChoiceId());
        return "update-selected-question";
    }

    @PostMapping("/edited-question")
    public String updateQuestion(@RequestParam(name = "question_id") int question_id,
                                 @RequestParam(name = "category_id") int category_id,
                                 @RequestParam(name = "description") String description) {
        questionService.updateQuestionById(question_id, category_id, description);
        return "redirect:/success-redirect-home";
    }
}