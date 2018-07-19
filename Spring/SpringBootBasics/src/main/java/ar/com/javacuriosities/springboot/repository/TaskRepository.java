package ar.com.javacuriosities.springboot.repository;

import ar.com.javacuriosities.springboot.model.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findByDescription(String description);
}
