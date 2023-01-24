package com.example.springhw2.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Task {
    private  int id;
    private String title;
    private  String description;
    private boolean status;
    private String deadline;
}

