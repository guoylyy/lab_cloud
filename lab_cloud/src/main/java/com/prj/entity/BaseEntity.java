package com.prj.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
	int id;
	
//	Date create_time = new Date();
//	Date modify_time;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	@Column(nullable = false)
//	public Date getCreate_time() {
//		return create_time;
//	}
//
//	public void setCreate_time(Date create_time) {
//		this.create_time = create_time;
//	}
//
//	@Column(nullable = false)
//	public Date getModify_time() {
//		return modify_time;
//	}
//
//	public void setModify_time(Date modify_time) {
//		this.modify_time = modify_time;
//	}
}
