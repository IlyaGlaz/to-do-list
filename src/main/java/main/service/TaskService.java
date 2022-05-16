package main.service;

import main.entity.Task;
import main.exception.TaskNotFoundException;
import main.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    public List<Task> getAll() {
        Iterable<Task> iterable = taskRepo.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task t : iterable) {
            tasks.add(t);
        }
        return tasks;
    }

    public Task getOne(Long id) throws TaskNotFoundException {
        Task task = taskRepo.findById(id).get();
        if (task == null) {
            throw new TaskNotFoundException("Задача не была найдена");
        }
        return task;
    }

    public Task add(Task task) {
        return taskRepo.save(task);
    }

    public synchronized Task complete(Long id) {
        Task task = taskRepo.findById(id).get();
        task.setCompleted(!task.getCompleted());
        return taskRepo.save(task);
    }

    public void deleteAll() {
        taskRepo.deleteAll();
    }

    public Long delete(Long id) {
        taskRepo.deleteById(id);
        return id;
    }
}
