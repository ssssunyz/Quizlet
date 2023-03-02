package com.bfs.quizlet.controller;

import com.bfs.quizlet.domain.User;
import com.bfs.quizlet.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;

@Controller
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    // get contact us page
    @GetMapping("/contactUs")
    public String getContactUs(Model model) {
        return "contact-us";
    }

    // user has submitted message
    @PostMapping("/contactUs")
    public String postSubmitContact(@RequestParam String subject, @RequestParam String msg, HttpSession session) {
        User user = (User) session.getAttribute("user");
        contactService.storeContactMsg(user.getUser_id(), subject, msg);
        return "redirect:/success-redirect-home";
    }

    // redirect to home after sending message
    @GetMapping("/success-redirect-home")
    public String getSendMsgSuccess() {
        return "/success-redirect-home";
    }
}
