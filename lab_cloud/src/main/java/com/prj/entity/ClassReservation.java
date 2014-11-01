package com.prj.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "class_reservation")
public class ClassReservation extends BaseEntity {
	private String classReservationNo;
	private Account reservator;
	private Date timestamp;
	private Set<StudentClass> studentClasses = new HashSet<StudentClass>(0);
	private Set<LabPlan> labPlans = new HashSet<LabPlan>(0);
	
	@Column(nullable = false)
	public String getClassReservationNo() {
		return classReservationNo;
	}
	public void setClassReservationNo(String classReservationNo) {
		this.classReservationNo = classReservationNo;
	}
	
	@ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name = "classReservationId")
	@JsonIgnore
	public Account getReservator() {
		return reservator;
	}
	public void setReservator(Account reservator) {
		this.reservator = reservator;
	}
	
	@Column(nullable = false)
	@Type(type = "timestamp")
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "classReservation")
	public Set<StudentClass> getStudentClasses() {
		return studentClasses;
	}
	public void setStudentClasses(Set<StudentClass> studentClasses) {
		this.studentClasses = studentClasses;
	}
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "classReservation")
	public Set<LabPlan> getLabPlans() {
		return labPlans;
	}
	public void setLabPlans(Set<LabPlan> labPlans) {
		this.labPlans = labPlans;
	}
}
