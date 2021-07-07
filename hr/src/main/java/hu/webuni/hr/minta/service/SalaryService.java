package hu.webuni.hr.minta.service;

import org.springframework.stereotype.Service;

import hu.webuni.hr.minta.model.Employee;

@Service
public class SalaryService {

	private EmployeeService employeeService;

	public SalaryService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void setNewSalary(Employee employee) {
		int newSalary = employee.getSalary() * (100 + employeeService.getPayRaisePercent(employee)) / 100;
		employee.setSalary(newSalary);
	}

}
