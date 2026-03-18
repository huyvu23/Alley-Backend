package com.alley.alley.task.service;

import com.alley.alley.kpi.entity.Kpi;
import com.alley.alley.task.dto.TaskRequest;
import com.alley.alley.task.dto.TaskResponse;
import com.alley.alley.task.dto.TaskUpdateRequest;
import com.alley.alley.task.entity.Task;
import com.alley.alley.task.repository.TaskRepository;
import com.alley.alley.user.entity.User;
import com.alley.alley.user.repository.UserRepository;
import com.alley.alley.task.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public TaskResponse createTask(TaskRequest request) {
        User assignee = null;
        if (request.getAssigneeId() != null) {
            assignee = userRepository.findById(request.getAssigneeId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getAssigneeId()));
        }

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .dueDate(request.getDueDate())
                .assignee(assignee)
                .build();

        if (request.getKpi() != null) {
            Kpi kpi = Kpi.builder()
                    .name(request.getKpi().getName())
                    .price(request.getKpi().getPrice())
                    .description(request.getKpi().getDescription())
                    .build();
            task.setKpi(kpi);
        }

        Task savedTask = taskRepository.save(task);
        return mapToResponse(savedTask);
    }

    @Override
    @Transactional
    public TaskResponse updateTask(String id, TaskUpdateRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());
        task.setUpdatedAt(OffsetDateTime.now());

        if (request.getAssigneeId() != null) {
            User assignee = userRepository.findById(request.getAssigneeId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getAssigneeId()));
            task.setAssignee(assignee);
        } else {
            task.setAssignee(null);
        }

        if (request.getKpi() != null) {
            if (task.getKpi() == null) {
                task.setKpi(new Kpi());
            }
            task.getKpi().setName(request.getKpi().getName());
            task.getKpi().setPrice(request.getKpi().getPrice());
            task.getKpi().setDescription(request.getKpi().getDescription());
        } else {
            task.setKpi(null);
        }

        Task updatedTask = taskRepository.save(task);
        return mapToResponse(updatedTask);
    }

    @Override
    @Transactional(readOnly = true)
    public TaskResponse getTaskById(String id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        return mapToResponse(task);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TaskResponse> getAllTasks(Status status, Pageable pageable) {
        if (status != null) {
            return taskRepository.findByStatus(status, pageable).map(this::mapToResponse);
        }
        return taskRepository.findAll(pageable).map(this::mapToResponse);
    }

    @Override
    @Transactional
    public void deleteTask(String id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }

    private TaskResponse mapToResponse(Task task) {
        TaskResponse.UserResponse userResponse = null;
        if (task.getAssignee() != null) {
            userResponse = TaskResponse.UserResponse.builder()
                    .id(task.getAssignee().getId())
                    .firstName(task.getAssignee().getFirstName())
                    .lastName(task.getAssignee().getLastName())
                    .email(task.getAssignee().getEmail())
                    .build();
        }

        TaskResponse.KpiResponse kpiResponse = null;
        if (task.getKpi() != null) {
            kpiResponse = TaskResponse.KpiResponse.builder()
                    .id(task.getKpi().getId())
                    .name(task.getKpi().getName())
                    .price(task.getKpi().getPrice())
                    .description(task.getKpi().getDescription())
                    .build();
        }

        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .dueDate(task.getDueDate())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .completedAt(task.getCompletedAt())
                .assignee(userResponse)
                .kpi(kpiResponse)
                .build();
    }
}
