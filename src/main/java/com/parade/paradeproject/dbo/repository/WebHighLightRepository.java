package com.parade.paradeproject.dbo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parade.paradeproject.dbo.entity.WebHighLightEntity;

@Repository
public interface WebHighLightRepository extends JpaRepository<WebHighLightEntity, Long>{

}
