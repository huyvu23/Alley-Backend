package com.alley.alley.kpi.controller;

import com.alley.alley.common.dtos.ApiResponse;
import com.alley.alley.common.dtos.MetaResponse;
import com.alley.alley.kpi.dto.KpiRequest;
import com.alley.alley.kpi.dto.KpiResponse;
import com.alley.alley.kpi.dto.KpiUpdateRequest;
import com.alley.alley.kpi.service.KpiService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kpis")
@RequiredArgsConstructor
public class KpiController {

    private final KpiService kpiService;

    @PostMapping
    public ApiResponse<KpiResponse> createKpi(@RequestBody KpiRequest request) {
        return ApiResponse.success(kpiService.createKpi(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<KpiResponse> updateKpi(@PathVariable String id, @RequestBody KpiUpdateRequest request) {
        return ApiResponse.success(kpiService.updateKpi(id, request));
    }

    @GetMapping("/{id}")
    public ApiResponse<KpiResponse> getKpiById(@PathVariable String id) {
        return ApiResponse.success(kpiService.getKpiById(id));
    }

    @GetMapping
    public ApiResponse<List<KpiResponse>> getAllKpis(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page - 1, pageSize, sort);
        Page<KpiResponse> kpiPage = kpiService.getAllKpis(pageable);

        return ApiResponse.success(kpiPage.getContent(), MetaResponse.fromPage(kpiPage));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteKpi(@PathVariable String id) {
        kpiService.deleteKpi(id);
        return ApiResponse.success("Kpi deleted successfully");
    }
}
