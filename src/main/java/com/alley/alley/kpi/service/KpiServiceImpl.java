package com.alley.alley.kpi.service;

import com.alley.alley.kpi.dto.KpiRequest;
import com.alley.alley.kpi.dto.KpiResponse;
import com.alley.alley.kpi.dto.KpiUpdateRequest;
import com.alley.alley.kpi.entity.Kpi;
import com.alley.alley.kpi.repository.KpiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class KpiServiceImpl implements KpiService {

    private final KpiRepository kpiRepository;

    @Override
    @Transactional
    public KpiResponse createKpi(KpiRequest request) {
        Kpi kpi = Kpi.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .build();
        
        Kpi savedKpi = kpiRepository.save(kpi);
        return mapToResponse(savedKpi);
    }

    @Override
    @Transactional
    public KpiResponse updateKpi(String id, KpiUpdateRequest request) {
        Kpi kpi = kpiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kpi not found with id: " + id));

        if (request.getName() != null) kpi.setName(request.getName());
        if (request.getPrice() != null) kpi.setPrice(request.getPrice());
        if (request.getDescription() != null) kpi.setDescription(request.getDescription());
        kpi.setUpdatedAt(OffsetDateTime.now());

        Kpi updatedKpi = kpiRepository.save(kpi);
        return mapToResponse(updatedKpi);
    }

    @Override
    @Transactional(readOnly = true)
    public KpiResponse getKpiById(String id) {
        Kpi kpi = kpiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kpi not found with id: " + id));
        return mapToResponse(kpi);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<KpiResponse> getAllKpis(Pageable pageable) {
        return kpiRepository.findAll(pageable).map(this::mapToResponse);
    }

    @Override
    @Transactional
    public void deleteKpi(String id) {
        if (!kpiRepository.existsById(id)) {
            throw new RuntimeException("Kpi not found with id: " + id);
        }
        kpiRepository.deleteById(id);
    }

    private KpiResponse mapToResponse(Kpi kpi) {
        return KpiResponse.builder()
                .id(kpi.getId())
                .name(kpi.getName())
                .price(kpi.getPrice())
                .description(kpi.getDescription())
                .createdAt(kpi.getCreatedAt())
                .updatedAt(kpi.getUpdatedAt())
                .build();
    }
}
