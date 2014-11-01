package com.prj.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "lab_plan")
public class LabPlan extends BaseEntity {
	private enum LabStatus {
		COURSE, 
		STUDENT
	}

	private enum ReservationType {
		NONE, 
		COURSE, 
		STUDENT
	}

	private Date date;
	private Integer slotId;
	private LabStatus labStatus;
	private ReservationType reservationType;
	private String classReservationNo;
	private Class clazz;
	private Lab lab;
	private ClassReservation classReservation;
	private StudentReservation studentReservation;

	@Column(nullable = false)
	@Type(type = "date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(nullable = false)
	public Integer getSlotId() {
		return slotId;
	}

	public void setSlotId(Integer slotId) {
		this.slotId = slotId;
	}

	@Column(nullable = false)
	public LabStatus getLabStatus() {
		return labStatus;
	}

	public void setLabStatus(LabStatus labStatus) {
		this.labStatus = labStatus;
	}

	@Column(nullable = false)
	public ReservationType getReservationType() {
		return reservationType;
	}

	public void setReservationType(ReservationType reservationType) {
		this.reservationType = reservationType;
	}

	@Column(nullable = false)
	public String getClassReservationNo() {
		return classReservationNo;
	}

	public void setClassReservationNo(String classReservationNo) {
		this.classReservationNo = classReservationNo;
	}

	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "classId")
	@JsonIgnore
	public Class getClazz() {
		return clazz;
	}

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "labId")
	@JsonIgnore
	public Lab getLab() {
		return lab;
	}

	public void setLab(Lab lab) {
		this.lab = lab;
	}

	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "classReservationId")
	@JsonIgnore
	public ClassReservation getClassReservation() {
		return classReservation;
	}

	public void setClassReservation(ClassReservation classReservation) {
		this.classReservation = classReservation;
	}

	@ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name = "studentReservationId")
	@JsonIgnore
	public StudentReservation getStudentReservation() {
		return studentReservation;
	}

	public void setStudentReservation(StudentReservation studentReservation) {
		this.studentReservation = studentReservation;
	}


}
