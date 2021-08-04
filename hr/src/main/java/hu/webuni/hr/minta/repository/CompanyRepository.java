package hu.webuni.hr.minta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.minta.model.AverageSalaryByPosition;
import hu.webuni.hr.minta.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

	public List<Company> findByEmployeeWithSalaryHigherThan(int minSalary);
	
	public List<Company> findByEmployeeCountHigherThan(int minEmployeeCount);

	public List<AverageSalaryByPosition> findAverageSalariesByPosition(long companyId);
	
}
