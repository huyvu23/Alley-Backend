package com.alley.alley.kpi.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.hibernate.annotations.UuidGenerator;

import com.alley.alley.task.entity.Task;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "kpis")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Kpi {
    @Id
    @GeneratedValue // Hibernate sẽ tự hiểu khi dùng với @UuidGenerator
    @UuidGenerator // Tự động tạo UUID phía Java trước khi insert
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", precision = 19, scale = 4)
    private BigDecimal price;

    @Builder.Default
    @Column(name = "description")
    private String description = "";

    @OneToOne(mappedBy = "kpi")
    private Task task;

    @Builder.Default
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}
