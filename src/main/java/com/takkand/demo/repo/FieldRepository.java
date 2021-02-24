package com.takkand.demo.repo;

import com.takkand.demo.domain.Field;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepository extends JpaRepository<Field, Long> {
}
