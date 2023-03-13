package com.parade.paradeproject.dbo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.parade.paradeproject.util.dataSendModel.DtoPresentField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="CATEGORY")
@EntityListeners(value = AuditingEntityListener.class)
public class CategoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@DtoPresentField
	private Long id;
		
	@Column
	@DtoPresentField
	private String title;
	
	@Column
	@DtoPresentField
	private Integer sequence;
	
	@Column
	@DtoPresentField
	private Boolean status;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserAccountEntity userAccountEntity;
	
	@Column
	@CreatedDate
	private LocalDateTime CreateTime;
	
	@Column
	@LastModifiedDate
	private LocalDateTime UpdateTime;
}
