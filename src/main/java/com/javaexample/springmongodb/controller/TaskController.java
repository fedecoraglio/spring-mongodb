package com.javaexample.springmongodb.controller;

import com.javaexample.springmongodb.dto.TaskDto;
import com.javaexample.springmongodb.request.CreateRequest;
import com.javaexample.springmongodb.request.UpdateRequest;
import com.javaexample.springmongodb.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping()
    public TaskDto save(@RequestBody CreateRequest task) {
        return taskService.save(task);
    }

    @PutMapping("/{id}")
    public TaskDto update(@PathVariable String id, @RequestBody UpdateRequest taskRequest) {
        return taskService.update(id, taskRequest);
    }

    @DeleteMapping("/{id}")
    public TaskDto delete(@PathVariable String id) {
        return taskService.delete(id);
    }

    @GetMapping()
    public List<TaskDto> findAll() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public TaskDto findById(@PathVariable String id) {
        return taskService.findById(id);
    }

    @GetMapping("/severities/{severity}")
    public List<TaskDto> findBySeverity(@PathVariable int severity) {
        return taskService.findBySeverity(severity);
    }

    @GetMapping("/assignees/{assignee}")
    public List<TaskDto> findBySeverity(@PathVariable String assignee) {
        return taskService.findByAssignee(assignee);
    }

}
