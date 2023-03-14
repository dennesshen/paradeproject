package com.parade.paradeproject.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parade.paradeproject.dao.entity.RangeEntity;

@Repository
public interface RangeRepository extends JpaRepository<RangeEntity, Long>{
}
