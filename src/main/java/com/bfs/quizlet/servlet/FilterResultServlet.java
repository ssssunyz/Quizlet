package com.bfs.quizlet.servlet;

import com.bfs.quizlet.domain.Quiz;
import com.bfs.quizlet.service.QuizService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/FilterResultServlet")
public class FilterResultServlet extends HttpServlet {
    
    private final QuizService quizService;

    public FilterResultServlet(QuizService quizService) {
        this.quizService = quizService;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("In servlet: doPost");
        String categoryFilter = request.getParameter("categoryFilter");
        String userIdFilter = request.getParameter("userIdFilter");
        List<Quiz> allQuizzes = quizService.getAllQuiz();
        List<Quiz> filteredQuizzes = new ArrayList<>();
        for(Quiz q :allQuizzes){
            String categoryName = "";
            switch (q.getCategory_id())
            {
                case 1:
                    categoryName = "Java Core";
                    break;
                case 2:
                    categoryName = "Tennis";
                    break;
                case 3:
                    categoryName = "Fun Facts";
            }
            if(categoryName.startsWith(categoryFilter) && Integer.toString(q.getUser_id()).equals(userIdFilter)){
                filteredQuizzes.add(q);
            }
        }
        System.out.println("Filter Quiz: " + filteredQuizzes);

        // code to filter the all_quiz list based on categoryFilter and userIdFilter
        request.setAttribute("filteredQuizzes", filteredQuizzes);
        request.getRequestDispatcher("all-quiz-result.jsp").forward(request, response);
    }
}
