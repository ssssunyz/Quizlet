package com.bfs.quizlet.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuizQuestionChoice {
    private int qq_id;
    private int quiz_id;
    private int question_id;
    private int choice_id;
    private boolean is_selected;
    private boolean is_correct;
}