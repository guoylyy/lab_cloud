package com.prj.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "experiment_lab")
public class ExperimentLab extends BaseEntity {
	private Experiment experiment;
	private Boolean lab1;
	private Boolean lab2;
	private Boolean lab3;
	private Boolean lab4;
	private Boolean lab5;
	private Boolean lab6;
	private Boolean lab7;
	private Boolean lab8;
	private Boolean lab9;
	private Boolean lab10;
	
	@OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "experimentLab")
	public Experiment getExperiment() {
		return experiment;
	}
	public void setExperiment(Experiment experiment) {
		this.experiment = experiment;
	}
	public Boolean getLab1() {
		return lab1;
	}
	public void setLab1(Boolean lab1) {
		this.lab1 = lab1;
	}
	public Boolean getLab2() {
		return lab2;
	}
	public void setLab2(Boolean lab2) {
		this.lab2 = lab2;
	}
	public Boolean getLab3() {
		return lab3;
	}
	public void setLab3(Boolean lab3) {
		this.lab3 = lab3;
	}
	public Boolean getLab4() {
		return lab4;
	}
	public void setLab4(Boolean lab4) {
		this.lab4 = lab4;
	}
	public Boolean getLab5() {
		return lab5;
	}
	public void setLab5(Boolean lab5) {
		this.lab5 = lab5;
	}
	public Boolean getLab6() {
		return lab6;
	}
	public void setLab6(Boolean lab6) {
		this.lab6 = lab6;
	}
	public Boolean getLab7() {
		return lab7;
	}
	public void setLab7(Boolean lab7) {
		this.lab7 = lab7;
	}
	public Boolean getLab8() {
		return lab8;
	}
	public void setLab8(Boolean lab8) {
		this.lab8 = lab8;
	}
	public Boolean getLab9() {
		return lab9;
	}
	public void setLab9(Boolean lab9) {
		this.lab9 = lab9;
	}
	public Boolean getLab10() {
		return lab10;
	}
	public void setLab10(Boolean lab10) {
		this.lab10 = lab10;
	}
}
