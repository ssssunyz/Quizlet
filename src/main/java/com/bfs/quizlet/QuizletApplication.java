package com.bfs.quizlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class QuizletApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizletApplication.class, args);
    }
}
