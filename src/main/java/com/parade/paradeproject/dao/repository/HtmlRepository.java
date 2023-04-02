package com.parade.paradeproject.dao.repository;

import com.parade.paradeproject.dao.entity.HtmlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HtmlRepository extends JpaRepository<HtmlEntity, Long>{


}
