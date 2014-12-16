package com.prj.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "student_reservation")
public class StudentReservation extends BaseEntity {
	private Set<Student> students = new HashSet<Student>(0);
	private Set<LabPlan> labPlans = new HashSet<LabPlan>(0);

	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "studentReservation")
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, mappedBy = "studentReservation")
	public Set<LabPlan> getLabPlans() {
		return labPlans;
	}
	public void setLabPlans(Set<LabPlan> labPlans) {
		this.labPlans = labPlans;
	}
}
