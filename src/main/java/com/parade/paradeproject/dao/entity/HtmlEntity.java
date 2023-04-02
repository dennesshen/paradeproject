package com.parade.paradeproject.dao.entity;

import com.parade.paradeproject.dao.entity.base.EntityBase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "HTML")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class HtmlEntity extends EntityBase{

    public HtmlEntity(String regex) {
        this.regex = regex;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String regex;

}
