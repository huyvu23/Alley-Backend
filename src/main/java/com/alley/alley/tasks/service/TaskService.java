package com.alley.alley.tasks.service;

import com.alley.alley.tasks.dto.TaskRequest;
import com.alley.alley.tasks.dto.TaskResponse;
import com.alley.alley.tasks.dto.TaskUpdateRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {
    TaskResponse createTask(TaskRequest request);

    TaskResponse updateTask(String id, TaskUpdateRequest request);

    TaskResponse getTaskById(String id);

    Page<TaskResponse> getAllTasks(Pageable pageable);

    void deleteTask(String id);
}
