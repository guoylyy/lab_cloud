package com.prj.entity;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

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
	private Date entranceYearmonth;
	private Time lastLoginTime;
	private Boolean isActive;

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

	public String getAccountEmail() {
		return accountEmail;
	}

	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}

	@Enumerated(EnumType.STRING)
	public Character getAccountCharacter() {
		return accountCharacter;
	}

	public void setAccountCharacter(Character accountCharacter) {
		this.accountCharacter = accountCharacter;
	}

	public String getStudentGrade() {
		return studentGrade;
	}

	public void setStudentGrade(String studentGrade) {
		this.studentGrade = studentGrade;
	}

	public Date getEntranceYearmonth() {
		return entranceYearmonth;
	}

	public void setEntranceYearmonth(Date entranceYearmonth) {
		this.entranceYearmonth = entranceYearmonth;
	}

	@Column(nullable = false)
	public Time getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Time lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Column(nullable = false)
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
