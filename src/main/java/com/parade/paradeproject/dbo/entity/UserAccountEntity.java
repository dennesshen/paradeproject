package com.parade.paradeproject.dbo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.parade.paradeproject.util.dataSendModel.DtoPresentField;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Entity
@Table(name = "USERACCOUNT")
@ToString(exclude = "webNoteEntity")
public class UserAccountEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@DtoPresentField(group = {"b"})
	private Long id;

	@Column(name = "username")
    @DtoPresentField(group = {"b"})
	private String userName;

	@Column
	private String password;

	@Column
	private String authentication;


	@OneToMany(mappedBy = "userAccountEntity")
	private List<WebNoteEntity> webNoteEntity;

}
