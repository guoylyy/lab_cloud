package com.prj.entity;

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
@Table(name = "lab")
public class Lab extends BaseEntity {
	private enum Status {
		OPEN,
		CLOSED
	}
	
	private Integer capacity;
	private Status status;
	private Set<Experiment> experiments = new HashSet<Experiment>(0);
	private Set<LabPlan> labPlans = new HashSet<LabPlan>(0);

	@Column(nullable = false)
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	
	@Column(nullable = false)
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinTable(	name = "lab_experiment",
				joinColumns = {@JoinColumn(name = "labId")},
				inverseJoinColumns = {@JoinColumn(name = "experimentId")})
	public Set<Experiment> getExperiments() {
		return experiments;
	}
	public void setExperiments(Set<Experiment> experiments) {
		this.experiments = experiments;
	}
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinTable(	name = "lab_experiment",
				joinColumns = {@JoinColumn(name = "labId")},
				inverseJoinColumns = {@JoinColumn(name = "labPlanId")})
	public Set<LabPlan> getLabPlans() {
		return labPlans;
	}
	public void setLabPlans(Set<LabPlan> labPlans) {
		this.labPlans = labPlans;
	}
	

}
