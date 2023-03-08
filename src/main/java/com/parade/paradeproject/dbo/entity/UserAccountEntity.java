package com.parade.paradeproject.dbo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "USERACCOUNT")
public class UserAccountEntity {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column
	private String userName;
	
	@Column
	private String password;
	
	@Column
	private String authentication;
	
	
	@OneToMany(mappedBy = "userAccountEntity")
	private List<WebNoteEntity> webNoteEntity;
	
	
	@OneToMany(mappedBy = "userAccountEntity")
	private List<NoteEntity> noteEntities;
}
