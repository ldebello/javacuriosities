package ar.com.javacuriosities.springboot.service;

import ar.com.javacuriosities.springboot.model.Task;
import ar.com.javacuriosities.springboot.model.request.TaskRequest;
import ar.com.javacuriosities.springboot.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultTaskService implements TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public DefaultTaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task save(TaskRequest taskRequest) {
        Task task = new Task();
        task.setDescription(taskRequest.getDescription());
        task.setEffectiveDate(taskRequest.getEffectiveDate());
        return taskRepository.save(task);
    }

    @Override
    public Iterable<Task> findAll() {
        return taskRepository.findAll();
    }
}
