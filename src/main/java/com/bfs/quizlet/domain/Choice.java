package com.bfs.quizlet.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Choice {
    private int choice_id;
    private int question_id;
    private String choice_description;
    private boolean is_correct;
}