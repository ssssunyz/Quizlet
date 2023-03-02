package com.bfs.quizlet.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Question {
    private int question_id;
    private int category_id;
    private String description;
    private boolean is_active;
    private int correctChoiceId;
}
