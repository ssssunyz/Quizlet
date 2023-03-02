package com.bfs.quizlet.domain;

import lombok.*;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Feedback {
    private int feedback_id;
    private int user_id;
    private String text;
    private int rating;
    private Date date;
}