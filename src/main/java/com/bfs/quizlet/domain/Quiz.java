package com.bfs.quizlet.domain;

import lombok.*;
import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Quiz {
    private int quiz_id;
    private int user_id;
    private int category_id;
    private String name;
    private Time start_time;
    private Time end_time;
    private int score;
    private String detail;
}