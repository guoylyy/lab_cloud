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
@Table(name = "student_labplan")
public class StudentLabPlan extends BaseEntity {
	private StudentClass studentClass;
	private Set<StudentLabRecord> studentLabRecords = new HashSet<StudentLabRecord>(0);

	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "studentLabPlanId")
	@JsonIgnore
	public StudentClass getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(StudentClass studentClass) {
		this.studentClass = studentClass;
	}

	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "studentLabPlan")
	public Set<StudentLabRecord> getStudentLabRecords() {
		return studentLabRecords;
	}

	public void setStudentLabRecords(Set<StudentLabRecord> studentLabRecords) {
		this.studentLabRecords = studentLabRecords;
	}
}
