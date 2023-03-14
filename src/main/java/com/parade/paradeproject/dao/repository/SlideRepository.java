package com.parade.paradeproject.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parade.paradeproject.dao.entity.CategoryEntity;
import com.parade.paradeproject.dao.entity.SlideEntity;

@Repository
public interface SlideRepository extends JpaRepository<SlideEntity, Long>{


}
