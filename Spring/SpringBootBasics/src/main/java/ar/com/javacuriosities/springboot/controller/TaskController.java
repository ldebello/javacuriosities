package ar.com.javacuriosities.springboot.controller;

import ar.com.javacuriosities.springboot.exception.InvalidDescriptionException;
import ar.com.javacuriosities.springboot.model.Task;
import ar.com.javacuriosities.springboot.model.request.TaskRequest;
import ar.com.javacuriosities.springboot.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

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

    @RequestMapping(value = "paginated", method = RequestMethod.GET)
    public ResponseEntity findAllPaginated(
            @RequestParam(value = "page", defaultValue = "-1") int page,
            @RequestParam(value = "pageSize") Optional<Integer> pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize.orElse(2));
        Page<Task> tasks = taskService.findAll(pageable);
        return ResponseEntity.ok().body(tasks);
    }

    @RequestMapping(value = "/{description}", method = RequestMethod.GET)
    public ResponseEntity findByDescription(@PathVariable String description) {
        Iterable<Task> tasks = taskService.findByDescription(description);
        return ResponseEntity.ok().body(tasks);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody TaskRequest taskRequest) {
        String description = taskRequest.getDescription();
        if (description != null && description.contains("dropwizard")) {
            throw new InvalidDescriptionException("Description does not make any sense");
        }
        Task newTask = taskService.save(taskRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newTask.getId()).toUri();
        return ResponseEntity.created(location).body(newTask);
    }
}
