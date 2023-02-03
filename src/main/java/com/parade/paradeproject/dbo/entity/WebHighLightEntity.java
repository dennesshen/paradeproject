package com.parade.paradeproject.dbo.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.parade.paradeproject.util.dataSendModel.DtoPresentField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "WEBHIGHLIGHTENTITY")
public class WebHighLightEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@DtoPresentField
	private Long id;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@DtoPresentField
	private String slideId;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@DtoPresentField
	private String content;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@DtoPresentField
	private String color;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@DtoPresentField
	private String note;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@DtoPresentField
	private String coordinates;

	@Column(name="user_id", insertable = false, updatable = false)
	@DtoPresentField
	private Long userId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserAccountEntity userAccountEntity;

}
