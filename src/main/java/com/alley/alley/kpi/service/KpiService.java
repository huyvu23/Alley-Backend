package com.alley.alley.kpi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.alley.alley.kpi.dto.KpiRequest;
import com.alley.alley.kpi.dto.KpiResponse;
import com.alley.alley.kpi.dto.KpiUpdateRequest;

public interface KpiService {
    KpiResponse createKpi(KpiRequest request);
    KpiResponse updateKpi(String id, KpiUpdateRequest request);
    KpiResponse getKpiById(String id);
    Page<KpiResponse> getAllKpis(Pageable pageable);
    void deleteKpi(String id);
}
