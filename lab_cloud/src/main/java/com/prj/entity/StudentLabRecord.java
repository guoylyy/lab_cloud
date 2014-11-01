package com.prj.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "student_labrecord")
public class StudentLabRecord extends BaseEntity {
	private StudentLabPlan studentLabPlan;

	@ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name = "studentLabRecordId")
	@JsonIgnore
	public StudentLabPlan getStudentLabPlan() {
		return studentLabPlan;
	}

	public void setStudentLabPlan(StudentLabPlan studentLabPlan) {
		this.studentLabPlan = studentLabPlan;
	}	
	
}
