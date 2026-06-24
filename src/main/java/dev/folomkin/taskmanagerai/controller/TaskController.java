package dev.folomkin.taskmanagerai.controller;


import dev.folomkin.taskmanagerai.model.dto.TaskRequest;
import dev.folomkin.taskmanagerai.model.entity.TaskEntity;
import dev.folomkin.taskmanagerai.service.TaskService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController( TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping("/ai/tasks")
    public TaskEntity createFromPrompt(@RequestBody String rawPrompt) {
        return taskService.processAndSaveTask(rawPrompt);
    }


    @GetMapping("/ai/tasks")
    public List<TaskEntity> findAllTasks() {
        return taskService.findAllTasks();
    }


    @PostMapping("/ai/task-test")
    public TaskEntity saveTask(@RequestBody TaskRequest request) {
        return taskService.save(request);
    }
}
