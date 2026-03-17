package com.alley.alley.tasks.entity;

import java.time.OffsetDateTime;

import org.hibernate.annotations.UuidGenerator;

import com.alley.alley.kpi.entity.Kpi;
import com.alley.alley.tasks.enums.Priortity;
import com.alley.alley.tasks.enums.Status;
import com.alley.alley.user.entity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tasks")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue // Hibernate sẽ tự hiểu khi dùng với @UuidGenerator
    @UuidGenerator // Tự động tạo UUID phía Java trước khi insert
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private Status status = Status.PENDING;

    @Builder.Default
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(name = "updated_at", nullable = true)
    private OffsetDateTime updatedAt;

    @Column(name = "due_date", nullable = true)
    private OffsetDateTime dueDate;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false, length = 50)
    private Priortity priority = Priortity.MEDIUM;

    @Column(name = "completed_at", nullable = true)
    private OffsetDateTime completedAt;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "kpi_id", referencedColumnName = "id")
    private Kpi kpi;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;
}
