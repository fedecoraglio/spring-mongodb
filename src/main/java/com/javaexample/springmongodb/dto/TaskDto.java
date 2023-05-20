package com.javaexample.springmongodb.dto;

import lombok.Data;

@Data
public class TaskDto {
    private String id;
    private String description;
    private int severity;
    private String assignee;
    private int storyPoint;
}
