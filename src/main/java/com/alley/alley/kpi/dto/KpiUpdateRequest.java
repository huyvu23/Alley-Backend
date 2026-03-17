package com.alley.alley.kpi.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KpiUpdateRequest {
    private String name;
    private BigDecimal price;
    private String description;
}
