package com.example.task_service;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    private List<Task> tasks = new ArrayList<>();

    @GetMapping("/")
    public List<Task> getTasks() {
        return tasks;
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable int id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @PostMapping("/")
    public Task createTask(@RequestBody Task task) {
        tasks.add(task);
        return task;
    }
}

