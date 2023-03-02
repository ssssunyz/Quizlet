package com.bfs.quizlet.controller;

import com.bfs.quizlet.domain.User;
import com.bfs.quizlet.service.FeedbackService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    // get feedback page
    @GetMapping("/feedback")
    public String getFeedbackPage(Model model) {
        return "feedback";
    }

    // user has submitted feedback
    @PostMapping("/feedback")
    public String postSubmitContact(@RequestParam int rating,
                                    @RequestParam String text,
                                    HttpSession session) {
        User user = (User) session.getAttribute("user");
        LocalDate date = LocalDate.now();
        feedbackService.storeFeedback(user.getUser_id(), text, rating, date);
        return "redirect:/success-redirect-home";
    }

    // ContactController已经对 "/success-redirect-home" map过了
    // redirect to home after sending message
//    @GetMapping("/success-redirect-home")
//    public String getSendFeedbackSuccess() {
//        return "/success-redirect-home";
//    }
}
