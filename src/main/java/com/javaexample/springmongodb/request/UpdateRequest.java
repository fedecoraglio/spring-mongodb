package com.javaexample.springmongodb.request;

import lombok.Data;

@Data
public class UpdateRequest {
    private String description;
    private int severity;
    private String assignee;
    private int storyPoint;
}
