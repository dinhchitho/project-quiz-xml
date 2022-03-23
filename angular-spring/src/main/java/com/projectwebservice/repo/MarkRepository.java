package com.projectwebservice.repo;

import com.projectwebservice.model.exam.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkRepository extends JpaRepository<Mark, Long> {
}
