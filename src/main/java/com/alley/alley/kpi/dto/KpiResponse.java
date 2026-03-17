package com.alley.alley.kpi.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KpiResponse {
    private String id;
    private String name;
    private BigDecimal price;
    private String description;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
