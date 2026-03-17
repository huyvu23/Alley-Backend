package com.alley.alley.kpi.repository;

import com.alley.alley.kpi.entity.Kpi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KpiRepository extends JpaRepository<Kpi, String> {
}
