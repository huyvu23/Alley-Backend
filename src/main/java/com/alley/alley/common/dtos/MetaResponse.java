package com.alley.alley.common.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MetaResponse {
    private int page;
    private int pageSize;
    private long totalElements;
    private int totalPages;

    public static MetaResponse fromPage(Page<?> page) {
        return MetaResponse.builder()
                .page(page.getNumber() + 1)
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }
}
