package ar.com.javacuriosities.springboot.service;

import ar.com.javacuriosities.springboot.model.Task;
import ar.com.javacuriosities.springboot.model.request.TaskRequest;

public interface TaskService {
    Task save(TaskRequest taskRequest);

    Iterable<Task> findAll();

    Iterable<Task> findByDescription(String description);
}
