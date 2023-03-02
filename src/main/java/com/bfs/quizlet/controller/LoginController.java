package com.bfs.quizlet.controller;

import com.bfs.quizlet.domain.User;
import com.bfs.quizlet.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    // = @RequestMapping(method = RequestMethod.GET).
    // 所有对login的GET request都会match到这里 eg. 打开/login页面的时候
    @GetMapping("/login")
    public String getLogin(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        // redirect to /home if user is already logged in
        if (session != null && session.getAttribute("user") != null) {
            return "redirect:/home";
        }

        return "login";
    }

    // = @RequestMapping(method = RequestMethod.POST).
    // 所有对login的POST request都会match到这里 eg. 用户输入账户密码后点submit
    // validate that we are always getting a new session after login
    @PostMapping("/login")
    public String postLogin(@RequestParam String email,
                            @RequestParam String password,
                            HttpServletRequest request) {

        Optional<User> possibleUser = loginService.validateLogin(email, password);

        if(possibleUser.isPresent()) {
            HttpSession oldSession = request.getSession(false);
            // invalidate old session if it exists
            if (oldSession != null) oldSession.invalidate();

            // generate new session
            HttpSession newSession = request.getSession(true);

            // store user details in session
            newSession.setAttribute("user", possibleUser.get());

            return "redirect:/home";
        } else { // if user details are invalid
            return "login";
        }
    }

    @GetMapping("/register")
    public String getRegisterPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

//        // redirect to /home if user is already logged in
//        if (session != null && session.getAttribute("user") != null) {
//            return "redirect:/home";
//        }

        // if user is already logged in, invalidate session
        if (session != null && session.getAttribute("user") != null) {
            session.invalidate();
        }

        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@RequestParam String firstname, @RequestParam String lastname,
                               @RequestParam String email, @RequestParam String password,
                               @RequestParam String phone, HttpServletRequest request) {
        System.out.println("Here: In postRegister");
        Optional<User> possibleUser = loginService.userExist(email);

        if(possibleUser.isPresent()) {
            // User Already Exist
            System.out.println("User Already Exist");
            return "redirect:/register-fail";
        } else {
            // User Not Exist, create new user
            loginService.createNewUser(firstname, lastname, email, password, phone, true, false);
            System.out.println("Register Successful");
            return "redirect:/register-success";
        }
    }

    @GetMapping("/register-fail")
    public String getUserExist(HttpServletRequest request, Model model) {
        return "register-fail";
    }

    @GetMapping("/register-success")
    public String getRegisterSuccess(HttpServletRequest request, Model model) {
        return "register-success";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        HttpSession oldSession = request.getSession(false);
        // invalidate old session if it exists
        if(oldSession != null) oldSession.invalidate();
        return "login";
    }
}
