package main.repository;

import main.entity.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepo extends CrudRepository<Task, Long> {
}