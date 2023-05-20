package com.javaexample.springmongodb.mapper;

import com.javaexample.springmongodb.dto.TaskDto;
import com.javaexample.springmongodb.model.Task;
import com.javaexample.springmongodb.request.CreateRequest;
import com.javaexample.springmongodb.request.UpdateRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {
    @Autowired
    private ModelMapper modelMapper;

    public TaskDto mapEntityToDto(final Task task) {
        return modelMapper.map(task, TaskDto.class);
    }
    public Task mapRequestToEntity(final CreateRequest taskRequest) {
        return modelMapper.map(taskRequest, Task.class);
    }
    public Task mapRequestToEntity(final UpdateRequest taskRequest) {
        return modelMapper.map(taskRequest, Task.class);
    }
    public List<TaskDto> mapEntitiesToDtos(final List<Task> tasks) {
        final List<TaskDto> dtoTasks;
        if(tasks != null && tasks.size() > 0) {
            dtoTasks = tasks.stream().map(source -> mapEntityToDto(source)).collect(Collectors.toList());
        } else {
            dtoTasks = Collections.emptyList();
        }
        return dtoTasks;
    }
}
