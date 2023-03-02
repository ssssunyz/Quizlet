package com.bfs.quizlet.service;

import com.bfs.quizlet.dao.QuizQuestionChoiceDao;
import org.springframework.stereotype.Service;

@Service
public class QuizQuestionChoiceService {
    private final QuizQuestionChoiceDao quizQuestionChoiceDao;

    public QuizQuestionChoiceService(QuizQuestionChoiceDao quizQuestionChoiceDao) {
        this.quizQuestionChoiceDao = quizQuestionChoiceDao;
    }

    public void storeQQC() {

    }
}
