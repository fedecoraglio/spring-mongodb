package com.javaexample.springmongodb.repository;

import com.javaexample.springmongodb.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findBySeverity(final int severity);

    @Query("{assignee: ?0}")
    List<Task> getTaskByAssignee(final String assignee);
}
