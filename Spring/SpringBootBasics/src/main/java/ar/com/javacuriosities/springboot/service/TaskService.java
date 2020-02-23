package ar.com.javacuriosities.springboot.service;

import ar.com.javacuriosities.springboot.model.Task;
import ar.com.javacuriosities.springboot.model.request.TaskRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {
    Task save(TaskRequest taskRequest);

    Iterable<Task> findAll();

    Page<Task> findAll(Pageable pageable);

    Iterable<Task> findByDescription(String description);
}
