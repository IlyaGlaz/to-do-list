package main.controller;

import main.entity.Task;
import main.exception.TaskNotFoundException;
import main.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity getAllTasks() {
        try {
            return ResponseEntity.ok(taskService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getTask(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(taskService.getOne(id));
        } catch (TaskNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping
    public ResponseEntity addTask(@RequestBody Task task) {
        try {
            taskService.add(task);
            return ResponseEntity.ok("Задача добавлена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping
    public ResponseEntity completeTask(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(taskService.complete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping
    public ResponseEntity deleteAllTasks() {
        try {
            taskService.deleteAll();
            return ResponseEntity.ok("В вашем списке больше нет дел");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(taskService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
