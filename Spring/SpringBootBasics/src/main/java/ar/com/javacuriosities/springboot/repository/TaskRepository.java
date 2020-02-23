package ar.com.javacuriosities.springboot.repository;

import ar.com.javacuriosities.springboot.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    Page<Task> findAll(Pageable pageable);

    List<Task> findByDescription(String description);
}
