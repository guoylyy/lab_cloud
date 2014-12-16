package com.prj.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "student_class")
public class StudentClass extends BaseEntity {
	private Set<Student> students = new HashSet<Student>(0);
	private Set<Class> classes = new HashSet<Class>(0);
	private ClassReservation classReservation;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "studentClass")
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "studentClass")
	public Set<Class> getClasses() {
		return classes;
	}
	public void setClasses(Set<Class> classes) {
		this.classes = classes;
	}
	
	
	@ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name = "studentClassId")
	@JsonIgnore
	public ClassReservation getClassReservation() {
		return classReservation;
	}
	public void setClassReservation(ClassReservation classReservation) {
		this.classReservation = classReservation;
	}
}
