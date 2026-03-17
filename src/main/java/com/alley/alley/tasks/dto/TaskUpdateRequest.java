package com.alley.alley.tasks.dto;

import com.alley.alley.tasks.enums.Priortity;
import com.alley.alley.tasks.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskUpdateRequest {

    private String title;

    private String description;

    private Status status;

    private Priortity priority;
    private OffsetDateTime dueDate;
    private String assigneeId;
    private KpiRequest kpi;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class KpiRequest {
        private String name;
        private BigDecimal price;
        private String description;
    }
}