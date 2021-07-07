package hu.webuni.hr.minta.model;

import java.time.LocalDateTime;

public class Employee {

	private Long employeeId;
	private String name;
	private String jobTitle;
	private int salary;
	private LocalDateTime dateOfStartWork;
	
	public Employee() {
	}

	public Employee(Long employeeId, String name, String jobTitle, int salary, LocalDateTime dateOfStartWork) {
		this.employeeId = employeeId;
		this.name = name;
		this.jobTitle = jobTitle;
		this.salary = salary;
		this.dateOfStartWork = dateOfStartWork;
	}

	public Employee(int salary, LocalDateTime dateOfStartWork) {
		this.salary = salary;
		this.dateOfStartWork = dateOfStartWork;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public LocalDateTime getDateOfStartWork() {
		return dateOfStartWork;
	}

	public void setDateOfStartWork(LocalDateTime dateOfStartWork) {
		this.dateOfStartWork = dateOfStartWork;
	}

}
