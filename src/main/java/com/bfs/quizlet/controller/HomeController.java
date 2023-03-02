package com.bfs.quizlet.controller;

import com.bfs.quizlet.domain.Category;
import com.bfs.quizlet.domain.Quiz;
import com.bfs.quizlet.domain.User;
import com.bfs.quizlet.service.CategoryService;
import com.bfs.quizlet.service.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Time;
import java.util.List;

@Controller
public class HomeController {
    private final QuizService quizService;
    private final CategoryService categoryService;

    public HomeController(QuizService quizService, CategoryService categoryService) {
        this.quizService = quizService;
        this.categoryService = categoryService;
    }

    @GetMapping("/home")
    public String getHomePage(HttpServletRequest request, Model model) {
        /* getSession(false): retrieves the current session, and if doesn't exist, returns null
           getSession()/getSession(true): if doesn't exist, create one */
        HttpSession session = request.getSession(false);

        // redirect to /login if user has not logged in
        if (session == null) return "redirect:/login";

        User user = (User) session.getAttribute("user");  // 是在LoginController的postLogin里set的:
                                                      // newSession.setAttribute("user", possibleUser.get());
        // is admin
        if (user.is_admin()) return "redirect:/admin-home";

        // is regular user
        // display all quizzes taken by this user
        List<Quiz> quizzes_taken = quizService.getQuizByUser(user.getUser_id());
        model.addAttribute("quizzes_taken", quizzes_taken);

        // display all category names
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);
        return "home";  // return home.jsp
    }

    // user has selected category, redirect to corresponding quiz
    @PostMapping("/home")
    public String postSelectCategory(@RequestParam(name = "selectedCategoryId") int selectedCategoryId
            , HttpServletRequest request) {
        // System.out.println("In HomeController: postSelectCategory");
        // record start_time
        HttpSession session = request.getSession(false);
        session.setAttribute("start_time", System.currentTimeMillis());
        return "redirect:/quiz/" + selectedCategoryId;
    }
}