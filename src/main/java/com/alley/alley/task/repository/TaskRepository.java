package com.alley.alley.task.repository;

import com.alley.alley.task.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alley.alley.task.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {
    Page<Task> findByStatus(Status status, Pageable pageable);
}
