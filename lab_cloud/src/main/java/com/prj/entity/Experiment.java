package com.prj.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "experiment")
public class Experiment extends BaseEntity {
	private String experimentName;
	private Integer minimumStudent;
	private Integer maximumStudent;
	private Set<Course> courses = new HashSet<Course>(0);
	private Set<Lab> labs = new HashSet<Lab>(0);
	
	@Column(nullable = false)
	public String getExperimentName() {
		return experimentName;
	}
	public void setExperimentName(String experimentName) {
		this.experimentName = experimentName;
	}
	
	@Column(nullable = false)
	public Integer getMinimumStudent() {
		return minimumStudent;
	}
	public void setMinimumStudent(Integer minimumStudent) {
		this.minimumStudent = minimumStudent;
	}
	
	@Column(nullable = false)
	public Integer getMaximumStudent() {
		return maximumStudent;
	}
	public void setMaximumStudent(Integer maximumStudent) {
		this.maximumStudent = maximumStudent;
	}
	
	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "experiments")
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "experiments")
	public Set<Lab> getLabs() {
		return labs;
	}
	public void setLabs(Set<Lab> labs) {
		this.labs = labs;
	}
}
