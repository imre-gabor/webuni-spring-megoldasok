package hu.webuni.hr.minta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.minta.model.Company;
import hu.webuni.hr.minta.model.Employee;
import hu.webuni.hr.minta.repository.CompanyRepository;
import hu.webuni.hr.minta.repository.EmployeeRepository;

@Service
public class CompanyService  {
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;

	public Company save(Company company) {
		return companyRepository.save(company);
	}

	public Company update(Company company) {
		if(!companyRepository.existsById(company.getId()))
			return null;
		return companyRepository.save(company);
	}

	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	public Optional<Company> findById(long id) {
		return companyRepository.findById(id);
	}

	public void delete(long id) {
		companyRepository.deleteById(id);
	}

	public Company addEmployee(long id, Employee employee) {
		Company company = findById(id).get();
		company.addEmployee(employee);
		
		// companyRepository.save(company); --> csak cascade merge esetén működik
		employeeRepository.save(employee);
		
		return company;
	}
	
	public Company deleteEmployee(long id, long employeeId) {
		Company company = findById(id).get();
		Employee employee = employeeRepository.findById(employeeId).get();
		employee.setCompany(null);
		company.getEmployees().remove(employee);
		employeeRepository.save(employee);
		return company;
	}
	
	public Company replaceEmployees(long id, List<Employee> employees) {
		Company company = findById(id).get();
		company.getEmployees().forEach(emp -> emp.setCompany(null));
		company.getEmployees().clear();
		
		employees.forEach(emp ->{
			company.addEmployee(emp);
			Employee savedEmployee = employeeRepository.save(emp);
			company.getEmployees()
				.set(company.getEmployees().size()-1, savedEmployee);
		});
		
		return company;
	}
	
}
