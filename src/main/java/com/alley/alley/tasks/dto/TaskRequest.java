package com.alley.alley.tasks.dto;

import jakarta.validation.constraints.NotBlank;
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
public class TaskRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

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
