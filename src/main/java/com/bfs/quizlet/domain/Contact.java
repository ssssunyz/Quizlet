package com.bfs.quizlet.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contact {
    private int contact_id;
    private int user_id;
    private String subject;
    private String message;
}