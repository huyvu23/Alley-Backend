package com.alley.alley.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.alley.alley.task.enums.Priortity;
import com.alley.alley.task.enums.Status;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {
    private String id;
    private String title;
    private String description;
    private Status status;
    private Priortity priority;
    private OffsetDateTime dueDate;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private OffsetDateTime completedAt;
    private UserResponse assignee;
    private KpiResponse kpi;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserResponse {
        private String id;
        private String firstName;
        private String lastName;
        private String email;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class KpiResponse {
        private String id;
        private String name;
        private BigDecimal price;
        private String description;
    }
}
