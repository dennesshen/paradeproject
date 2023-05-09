package com.parade.paradeproject.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parade.paradeproject.dao.entity.CategoryEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{

    List<CategoryEntity> findByUserId(Long userId);

    Optional<CategoryEntity> findByIdAndUserId(Long id, Long userId);
}
