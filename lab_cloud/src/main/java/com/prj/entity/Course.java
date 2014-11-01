package com.prj.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course extends BaseEntity {
	private String courseName;
	private String department;
	private Date startYear;
	private Set<Class> classes = new HashSet<Class>(0);
	private Set<Experiment> experiments = new HashSet<Experiment>(0);
	
	@Column(nullable = false)
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	@Column(nullable = false)
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Column(nullable = false)
	public Date getStartYear() {
		return startYear;
	}
	public void setStartYear(Date startYear) {
		this.startYear = startYear;
	}
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "course")
	public Set<Class> getClasses() {
		return classes;
	}
	public void setClasses(Set<Class> classes) {
		this.classes = classes;
	}
	
	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinTable(name = "course_experiment", 
				joinColumns = {@JoinColumn(name = "courseId", referencedColumnName = "id")}, 
				inverseJoinColumns = {@JoinColumn(name = "experimentId", referencedColumnName = "id")})
	public Set<Experiment> getExperiments() {
		return experiments;
	}
	public void setExperiments(Set<Experiment> experiments) {
		this.experiments = experiments;
	}
}
