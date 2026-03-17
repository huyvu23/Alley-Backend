package com.alley.alley.task.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alley.alley.task.dto.TaskRequest;
import com.alley.alley.task.dto.TaskResponse;
import com.alley.alley.task.dto.TaskUpdateRequest;

public interface TaskService {
    TaskResponse createTask(TaskRequest request);

    TaskResponse updateTask(String id, TaskUpdateRequest request);

    TaskResponse getTaskById(String id);

    Page<TaskResponse> getAllTasks(Pageable pageable);

    void deleteTask(String id);
}
