package hu.webuni.hr.minta.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import hu.webuni.hr.minta.model.Company;
import hu.webuni.hr.minta.model.Employee;
import hu.webuni.hr.minta.model.Position;
import hu.webuni.hr.minta.repository.EmployeeRepository;
import hu.webuni.hr.minta.repository.PositionRepository;

@Service
public abstract class AbstractEmployeeService implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	PositionService positionService;
	
	@Override
	@Transactional
	public Employee save(Employee employee) {
		clearCompanyAndSetPosition(employee);
		return employeeRepository.save(employee);
	}

	private void clearCompanyAndSetPosition(Employee employee) {
		employee.setCompany(null);
		positionService.setPositionForEmployee(employee);
	}

	
	
	@Override
	@Transactional
	public Employee update(Employee employee) {
		if(!employeeRepository.existsById(employee.getEmployeeId()))
			return null;
		clearCompanyAndSetPosition(employee);	
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> findById(long id) {
		return employeeRepository.findById(id);
	}

	@Override
	public void delete(long id) {
		employeeRepository.deleteById(id);
	}
	
	@Override
	public List<Employee> findEmployeesByExample(Employee example) {
		long id = example.getEmployeeId();
		String name = example.getName();
		String title = example.getPosition().getName();
		int salary = example.getSalary();
		LocalDateTime entryDate = example.getDateOfStartWork();
		Company company = example.getCompany();
		String companyName = company == null ? null : company.getName();

		Specification<Employee> spec = Specification.where(null);

		if (id > 0)
			spec = spec.and(EmployeeSpecifications.hasId(id));

		if (StringUtils.hasText(name))
			spec = spec.and(EmployeeSpecifications.hasName(name));

		if (StringUtils.hasText(title))
			spec = spec.and(EmployeeSpecifications.hasTitle(title));

		if (salary > 0)
			spec = spec.and(EmployeeSpecifications.hasSalary(salary));

		if (entryDate != null)
			spec = spec.and(EmployeeSpecifications.hasEntryDate(entryDate));

		if (StringUtils.hasText(companyName))
			spec = spec.and(EmployeeSpecifications.hasCompany(companyName));

		return employeeRepository.findAll(spec, Sort.by("employeeId"));
	}
 
	
}
