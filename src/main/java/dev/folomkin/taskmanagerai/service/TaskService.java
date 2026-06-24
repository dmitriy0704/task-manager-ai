package dev.folomkin.taskmanagerai.service;


import dev.folomkin.taskmanagerai.model.dto.TaskRequest;
import dev.folomkin.taskmanagerai.model.entity.TaskEntity;
import dev.folomkin.taskmanagerai.repository.TaskRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ChatClient chatClient;

    public TaskService(TaskRepository taskRepository,
                       ChatClient.Builder chatClientBuilder) {
        this.taskRepository = taskRepository;
        this.chatClient = chatClientBuilder.build();
    }


    public TaskEntity processAndSaveTask(String request) {
        TaskRequest result = this.chatClient
                .prompt()
                .user("Извлеки данные из следующего текста и сформируй задачу. " +
                        "Текст: " + request)
                .call()
                .entity(TaskRequest.class);

        TaskEntity entity = new TaskEntity();
        entity.setTaskTitle(result.taskTitle());
        entity.setTaskDescription(result.taskDescription());
        return taskRepository.save(entity);
    }


    public List<TaskEntity> findAllTasks() {
        return this.taskRepository.findAll();
    }


    // Тестовый запрос
    public TaskEntity save(TaskRequest request) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTaskTitle(request.taskTitle());
        taskEntity.setTaskDescription(request.taskDescription());
        return taskRepository.save(taskEntity);
    }
}
