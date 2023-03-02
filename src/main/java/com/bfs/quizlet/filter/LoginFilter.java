package com.bfs.quizlet.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("Here: In LoginFilter");
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) {  // if logged in
            filterChain.doFilter(request, response);
        } else {
            // if not logged in, redirect to the login page
            response.sendRedirect("/login");
        }
    }

    @Override
    // 如果已经在login页面 或点击了register 就不用filter了
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.equals("/login") || path.equals("/register") || path.equals("/register-fail")
                || path.equals("/register-success") || path.equals("/success-redirect-home")
//                || path.equals("/quiz-result")
                ;
    }
}
