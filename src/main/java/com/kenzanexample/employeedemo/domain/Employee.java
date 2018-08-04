package com.kenzanexample.employeedemo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {
	
/*
 * Employee spec
 *  ID - Unique identifier for an employee
 *  FirstName - Employee first name
 *  MiddleInitial - Employee middle initial
 *  LastName - Employee last name
 *  DateOfBirth - Employee birthday and year
 *  DateOfEmployment - Employee start date
 *  Status - ACTIVE or INACTIVE
 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique=true, nullable=false, length=255)
	private long ID;
	
	@Column(name = "first_name", unique=false, nullable=false, length=60)
	private String FirstName;
	
	@Column(name = "middle_initial", unique=false, nullable=true, length=1)
	private String MiddleInitial;
	
	@Column(name = "last_name", unique=false, nullable=false, length=60)
	private String LastName;
	
	@Column(name = "date_of_birth", unique=false, nullable=false, length=60)
	private String DateOfBirth;
	
	@Column(name = "date_of_employment", unique=false, nullable=false, length=60)
	private String DateOfEmployment;
	
	@Column(name = "status", unique=false, nullable=false, length=10)
	private String Status;
	
	public Employee () {}
	
	public Employee (Long ID, String FirstName, String MiddleInitial, String LastName, String DateOfBirth, String DateOfEmployment, String Status) {
		this.ID = ID;
		this.FirstName = FirstName;
		this.MiddleInitial = MiddleInitial;
		this.LastName = LastName;
		this.DateOfBirth = DateOfBirth;
		this.DateOfEmployment = DateOfEmployment;
		this.Status = Status;
	}
	
	public Long getID() {
		return ID;
	}
	
	public void setID(Long ID) {
		this.ID = ID;
	}

	public String getFirstName() {
		return FirstName;
	}
	
	public void setFirstName(String FirstName) {
		this.FirstName = FirstName;
	}
	
	public String getLastName() {
		return LastName;
	}
	
	public void setLastName(String LastName) {
		this.LastName = LastName;
	}
	
	public String getMiddleInitial() {
		return MiddleInitial;
	}
	
	public void setMiddleInitial(String MiddleInitial) {
		this.MiddleInitial = MiddleInitial;
	}
	
	public String getDateOfBirth() {
		return DateOfBirth;
	}
	
	public void setDateOfBirth(String DateOfBirth) {
		this.DateOfBirth = DateOfBirth;
	}
	
	public String getDateOfEmployment() {
		return DateOfEmployment;
	}
	
	public void setDateOfEmployment(String DateOfEmployment) {
		this.DateOfEmployment = DateOfEmployment;
	}
	
	public String getStatus() {
		return Status;
	}
	
	public void setStatus(String Status) {
		this.Status = Status;
	}
	
		
	@Override
	public String toString() {
		return "Employee [ID=" + ID + ", FirstName=" + FirstName + ", MiddleInitial=" + MiddleInitial + ", LastName=" + LastName + ", DateOfBirth=" 
				+ DateOfBirth + ", DateOfEmployment=" + DateOfEmployment + ", Status=" + Status + "]";

		}
	}
