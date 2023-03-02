package com.bfs.quizlet.service;

import com.bfs.quizlet.dao.QuizDao;
import com.bfs.quizlet.domain.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.List;

@Service
public class QuizService {

    private final QuizDao quizDao;

    @Autowired
    public QuizService(QuizDao quizDao) {
        this.quizDao = quizDao;
    }

    public List<Quiz> getAllQuiz() {
        return quizDao.getAllQuiz();
    }

    public Quiz getQuizByQuizId(int quiz_id) {
        return quizDao.getQuizByQuizId(quiz_id);
    }

    public List<Quiz> getQuizByUser(int user_id) {
        return quizDao.getQuizByUser(user_id);
    }

    public void storeQuizResult(int user_id, int category_id, String quiz_name,
                                Timestamp start_time, Timestamp end_time, int score, String detail) {
        quizDao.storeQuizResult(user_id, category_id, quiz_name, start_time, end_time, score, detail);
    }
}
