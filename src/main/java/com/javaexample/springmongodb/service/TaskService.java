package com.javaexample.springmongodb.service;

import com.javaexample.springmongodb.dto.TaskDto;
import com.javaexample.springmongodb.exception.SpringMongodbException;
import com.javaexample.springmongodb.mapper.TaskMapper;
import com.javaexample.springmongodb.model.Task;
import com.javaexample.springmongodb.repository.TaskRepository;
import com.javaexample.springmongodb.request.CreateTaskRequest;
import com.javaexample.springmongodb.request.UpdateTaskRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    public TaskDto save(final CreateTaskRequest taskRequest) {
        try {
            final Task task = this.taskMapper.mapRequestToEntity(taskRequest);
            task.setId(UUID.randomUUID().toString().split("-")[0]);
            return this.taskMapper.mapEntityToDto(taskRepository.save(task));
        } catch(final Exception ex) {
            throw new SpringMongodbException(SpringMongodbException.Type.TASK_ERROR_SAVE, ex);
        }
    }

    public List<TaskDto> findAll() {
        try {
            return this.taskMapper.mapEntitiesToDtos(taskRepository.findAll());
        } catch(final Exception ex) {
            throw new SpringMongodbException(SpringMongodbException.Type.TASK_ERROR_SEARCH, ex);
        }
    }

    public TaskDto findById(final String id) {
        try {
            final Task task = taskRepository.findById(id).get();
            return this.taskMapper.mapEntityToDto(task);
        } catch(final Exception ex) {
            throw new SpringMongodbException(SpringMongodbException.Type.TASK_IS_NOT_FOUND, ex);
        }
    }

    public List<TaskDto> findBySeverity(final int severity) {
        try {
            return this.taskMapper.mapEntitiesToDtos(taskRepository.findBySeverity(severity));
        } catch(final Exception ex) {
            throw new SpringMongodbException(SpringMongodbException.Type.TASK_ERROR_SEARCH_SEVERITY, ex);
        }
    }

    public List<TaskDto> findByAssignee(final String assignee) {
        try {
            return this.taskMapper.mapEntitiesToDtos(taskRepository.getTaskByAssignee(assignee));
        } catch(final Exception ex) {
            throw new SpringMongodbException(SpringMongodbException.Type.TASK_ERROR_ASSIGNEE, ex);
        }

    }

    public TaskDto update(final String id, final UpdateTaskRequest taskRequest) {
        final Optional<Task> optional = taskRepository.findById(id);
        if(!optional.isPresent()) {
            throw new SpringMongodbException(SpringMongodbException.Type.TASK_IS_NOT_FOUND);
        }
        try {
            final Task curentTask = optional.get();
            curentTask.setAssignee(taskRequest.getAssignee());
            curentTask.setDescription(taskRequest.getDescription());
            curentTask.setSeverity(taskRequest.getSeverity());
            curentTask.setStoryPoint(taskRequest.getStoryPoint());
            return this.taskMapper.mapEntityToDto(taskRepository.save(curentTask));
        } catch(final Exception ex) {
            throw new SpringMongodbException(SpringMongodbException.Type.TASK_ERROR_UPDATE, ex);
        }
    }
    public TaskDto delete(final String id) {
        final Optional<Task> optional = taskRepository.findById(id);
        if(!optional.isPresent()) {
            throw new SpringMongodbException(SpringMongodbException.Type.TASK_IS_NOT_FOUND);
        }
        try {
            final Task task = optional.get();
            taskRepository.deleteById(id);
            return this.taskMapper.mapEntityToDto(task);
        } catch(final Exception ex) {
            throw new SpringMongodbException(SpringMongodbException.Type.TASK_ERROR_UPDATE, ex);
        }
    }
}
