package hu.webuni.hr.minta.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.minta.model.Employee;
import hu.webuni.hr.minta.repository.EmployeeRepository;
import hu.webuni.hr.minta.repository.PositionDetailsByCompanyRepository;
import hu.webuni.hr.minta.repository.PositionRepository;

@Service
public class SalaryService {

	private EmployeeService employeeService;
	private PositionRepository positionRepository;
	private PositionDetailsByCompanyRepository positionDetailsByCompanyRepository;

	private EmployeeRepository employeeRepository;

	public SalaryService(EmployeeService employeeService, PositionRepository positionRepository,
			PositionDetailsByCompanyRepository positionDetailsByCompanyRepository,
			EmployeeRepository employeeRepository) {
		super();
		this.employeeService = employeeService;
		this.positionRepository = positionRepository;
		this.positionDetailsByCompanyRepository = positionDetailsByCompanyRepository;
		this.employeeRepository = employeeRepository;
	}

	public void setNewSalary(Employee employee) {
		int newSalary = employee.getSalary() * (100 + employeeService.getPayRaisePercent(employee)) / 100;
		employee.setSalary(newSalary);
	}
	
	@Transactional
	public void raiseMinimalSalary(String positionName, int minSalary, long companyId) {
		/* 1. megoldás, a minSalary még a Position tulajdonsága
		positionRepository.findByName(positionName)
		.forEach(p->{
			p.setMinSalary(minSalary);
			p.getEmployees().forEach(e ->{
				if(e.getSalary() < minSalary)
					e.setSalary(minSalary);
			});
		});*/
		
		/* 2. megoldás: itt már cégfüggő a pozícióhoz tartozó minimál fizetés, de a cég összes employee-ját
		 * betöltjük memóriába feleslegesen
		positionDetailsByCompanyRepository.findByPositionNameAndCompanyId(positionName, companyId)
		.forEach(pd ->{
			pd.setMinSalary(minSalary);
			
			pd.getCompany().getEmployees().forEach(e-> {
				if(e.getSalary() < minSalary && 
						e.getPosition() != null &&
						e.getPosition().getName().equals(positionName))
					e.setSalary(minSalary);
			});
		});*/
		
		/* 3. megoldás: hatékonyabb update query az érintett employee-kra*/
		positionDetailsByCompanyRepository.findByPositionNameAndCompanyId(positionName, companyId)
		.forEach(pd ->{
			pd.setMinSalary(minSalary);
		});
		employeeRepository.updateSalaries(positionName, minSalary, companyId);
	}

}
