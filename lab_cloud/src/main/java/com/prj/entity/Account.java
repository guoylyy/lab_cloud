package com.prj.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "account")
public class Account extends BaseEntity {
	private enum Character {
		ADMINISTRATOR,
		TEACHER,
		STUDENT
	}
	
	private String accountNumber;
	private String accountPassword;
	private String accountName;
	private String accountEmail;
	private Character accountCharacter;
	private String studentGrade;
	private Date entranceYearMonth;//used by student
	private Date lastLoginTime;
	private Boolean isActive = true;
	private String loginToken;
	private Set<Class> classes = new HashSet<Class>(0);//used by teacher
	private StudentClass studentClass;//used by student
	private StudentReservation studentReservation;//used by student
	
	@Column(nullable = false)
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Column(nullable = false)
	public String getAccountPassword() {
		return accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	@Column(nullable = false)
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Column
	public String getAccountEmail() {
		return accountEmail;
	}

	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	public Character getAccountCharacter() {
		return accountCharacter;
	}

	public void setAccountCharacter(Character accountCharacter) {
		this.accountCharacter = accountCharacter;
	}

	@Column
	public String getStudentGrade() {
		return studentGrade;
	}

	public void setStudentGrade(String studentGrade) {
		this.studentGrade = studentGrade;
	}

	@Column
	@Type(type = "date")
	public Date getEntranceYearMonth() {
		return entranceYearMonth;
	}

	public void setEntranceYearMonth(Date entranceYearMonth) {
		this.entranceYearMonth = entranceYearMonth;
	}

	@Type(type = "timestamp")
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Column(nullable = false)//columnDefinition="BIT default 1"
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Column
	public String getLoginToken() {
		return loginToken;
	}

	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}

	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "teacher")
	public Set<Class> getClasses() {
		return classes;
	}

	public void setClasses(Set<Class> classes) {
		this.classes = classes;
	}

	@ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name = "studentClassId")
	@JsonIgnore
	public StudentClass getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(StudentClass studentClass) {
		this.studentClass = studentClass;
	}

	@ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name = "studentReaservationId")
	@JsonIgnore
	public StudentReservation getStudentReservation() {
		return studentReservation;
	}

	public void setStudentReservation(StudentReservation studentReservation) {
		this.studentReservation = studentReservation;
	}
}
