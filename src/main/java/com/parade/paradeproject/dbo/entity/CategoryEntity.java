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

<<<<<<< HEAD:src/main/java/com/parade/paradeproject/dbo/entity/WebNoteEntity.java
import com.fasterxml.jackson.annotation.JsonIgnore;
=======
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

>>>>>>> origin/Category_module:src/main/java/com/parade/paradeproject/dbo/entity/CategoryEntity.java
import com.parade.paradeproject.util.dataSendModel.DtoPresentField;
import com.parade.paradeproject.util.dataSendModel.DtoPresentNextLevelData;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
<<<<<<< HEAD:src/main/java/com/parade/paradeproject/dbo/entity/WebNoteEntity.java
@ToString
@Table(name = "WEBNOTE")
public class WebNoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DtoPresentField(group = {"aaaa", "", "b"})
    private Long id;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @DtoPresentField(group = {"aaaa", "", "b"})
    private String text;

    @Column(name="user_id", insertable = false, updatable = false)
    @DtoPresentField
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @DtoPresentNextLevelData(name= "user", group = {"b"})
    private UserAccountEntity userAccountEntity;
    
    

=======
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
>>>>>>> origin/Category_module:src/main/java/com/parade/paradeproject/dbo/entity/CategoryEntity.java
}
