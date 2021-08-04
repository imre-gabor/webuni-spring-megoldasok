package hu.webuni.hr.minta.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.minta.model.AverageSalaryByPosition;
import hu.webuni.hr.minta.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

	@Query("SELECT DISTINCT c FROM Company c JOIN c.employees e WHERE e.salary > :minSalary")
	public Page<Company> findByEmployeeWithSalaryHigherThan(int minSalary, Pageable pageable);
	
	
	@Query("SELECT c FROM Company c WHERE SIZE(c.employees) > :minEmployeeCount")
	public List<Company> findByEmployeeCountHigherThan(int minEmployeeCount);

	
	@Query("SELECT e.position.name AS position, AVG(e.salary) AS averageSalary "
			+ "FROM Company c "
			+ "INNER JOIN c.employees e "
			+ "WHERE c.id= :companyId "
			+ "GROUP BY e.position.name "
			+ "ORDER BY avg(e.salary) DESC")
	public List<AverageSalaryByPosition> findAverageSalariesByPosition(long companyId);
	
}
