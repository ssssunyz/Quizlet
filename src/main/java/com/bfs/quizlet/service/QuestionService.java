package com.bfs.quizlet.service;

import com.bfs.quizlet.dao.QuestionDao;
import com.bfs.quizlet.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionDao questionDao;

    @Autowired
    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public void addQuestion(int categoryId, String description) {
        questionDao.addQuestion(categoryId, description);
    }

    public List<Question> getAllQuestionByCategory(int category_id) {
        return questionDao.getAllQuestionByCategory(category_id);
    }

    public List<Question> getFiveQuestionByCategory(int category_id) {
        return questionDao.getFiveQuestionByCategory(category_id);
    }

    public Question getQuestionById(int question_id) {
        return questionDao.getQuestionById(question_id);
    }

    public void changeQuestionStatus(int questionId, int changedStatus) {
        questionDao.changeQuestionStatus(questionId, changedStatus);
    }

    public void updateQuestionById(int question_id, int category_id, String description) {
        questionDao.updateQuestionById(question_id, category_id, description);
    }

//    public String checkAnswer(Choice selectedChoice) {
//        Question question = questionDao.getQuestion();
//        Choice correctChoice = question.getChoices().get(question.getCorrectChoiceId() - 1);
//        return selectedChoice.equals(correctChoice) ? "Correct!" : "Incorrect";
//    }

//    public Optional<Choice> getChoiceById(Integer selectedChoiceId) {
//        return questionDao
//                .getQuestion()
//                .getChoices()
//                .stream()
//                .filter(choice -> choice.getId() == selectedChoiceId)
//                .findFirst();
//    }
}
