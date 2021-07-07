package hu.webuni.hr.minta.dto;

import java.time.LocalDateTime;

public class EmployeeDto {
	private long id;
	private String name;
	private String title;
	private int salary;
	private LocalDateTime entryDate;

	public EmployeeDto() {

	}

	public EmployeeDto(long id, String name, String title, int salary, LocalDateTime entryDate) {
		this.id = id;
		this.name = name;
		this.title = title;
		this.salary = salary;
		this.entryDate = entryDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public LocalDateTime getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(LocalDateTime entryDate) {
		this.entryDate = entryDate;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", title=" + title + ", salary=" + salary + ", entryDate="
				+ entryDate + "]";
	}

}
