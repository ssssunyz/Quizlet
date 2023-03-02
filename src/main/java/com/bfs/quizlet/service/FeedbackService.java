package com.bfs.quizlet.service;

import com.bfs.quizlet.dao.FeedbackDao;
import com.bfs.quizlet.domain.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FeedbackService {
    private final FeedbackDao feedbackDao;

    @Autowired
    public FeedbackService(FeedbackDao feedbackDao) {
        this.feedbackDao = feedbackDao;
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackDao.getAllFeedbacks();
    }
    public void storeFeedback(int user_id, String text, int rating, LocalDate date) {
        feedbackDao.storeFeedback(user_id, text, rating, date);
    }
}
