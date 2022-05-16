package main.controller;

import main.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String listOfTasks(Model model) {
        model.addAttribute("tasks", taskService.getAll());
        return "index";
    }
}
