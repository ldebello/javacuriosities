package ar.com.javacuriosities.springboot.controller;

import ar.com.javacuriosities.springboot.model.Task;
import ar.com.javacuriosities.springboot.model.request.TaskRequest;
import ar.com.javacuriosities.springboot.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

import static java.lang.String.format;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity findAll() {
        Iterable<Task> tasks = taskService.findAll();
        return ResponseEntity.ok().body(tasks);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody TaskRequest taskRequest) {
        try {
            Task newTask = taskService.save(taskRequest);
            URI location = new URI(format(HttpHeaders.LOCATION, newTask.getId()));
            return ResponseEntity.created(location).body(newTask);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
