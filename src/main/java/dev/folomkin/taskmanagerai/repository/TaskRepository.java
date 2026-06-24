package dev.folomkin.taskmanagerai.repository;

import dev.folomkin.taskmanagerai.model.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
