package com.alley.alley.tasks.controller;

import com.alley.alley.common.dtos.ApiResponse;
import com.alley.alley.common.dtos.MetaResponse;
import com.alley.alley.tasks.dto.TaskRequest;
import com.alley.alley.tasks.dto.TaskResponse;
import com.alley.alley.tasks.dto.TaskUpdateRequest;
import com.alley.alley.tasks.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ApiResponse<TaskResponse> createTask(@RequestBody TaskRequest request) {
        return ApiResponse.success(taskService.createTask(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<TaskResponse> updateTask(@PathVariable String id, @RequestBody TaskUpdateRequest request) {
        return ApiResponse.success(taskService.updateTask(id, request));
    }

    @GetMapping("/{id}")
    public ApiResponse<TaskResponse> getTaskById(@PathVariable String id) {
        return ApiResponse.success(taskService.getTaskById(id));
    }

    @GetMapping
    public ApiResponse<List<TaskResponse>> getAllTasks(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page - 1, pageSize, sort);
        Page<TaskResponse> taskPage = taskService.getAllTasks(pageable);

        return ApiResponse.success(taskPage.getContent(), MetaResponse.fromPage(taskPage));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return ApiResponse.success("Task deleted successfully");
    }
}
