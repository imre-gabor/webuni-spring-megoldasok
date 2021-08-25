package hu.webuni.hr.minta.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.minta.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee>{

	List<Employee> findBySalaryGreaterThan(Integer minSalary);
	
	List<Employee> findByPositionName(String title);

	List<Employee> findByNameStartingWithIgnoreCase(String name);

	List<Employee> findByDateOfStartWorkBetween(LocalDateTime start, LocalDateTime end);

	
	@Modifying
	@Transactional //a mi konkrét példánkhoz nem kötelező, mert a service metódus már indít tranzakciót
//	@Query("UPDATE Employee e "
//			+ "SET e.salary = :minSalary "
//			+ "WHERE e.position.name = :positionName "
//			+ "AND e.company.id = :companyId")
	
	@Query("UPDATE Employee e "
			+ "SET e.salary = :minSalary "
			+ "WHERE e.id IN "
			+ "(SELECT e2.id "
			+ "FROM Employee e2 "
			+ "WHERE e2.position.name = :positionName "
			+ "AND e2.company.id = :companyId "
			+ "AND e2.salary < :minSalary"
			+ ")")
	void updateSalaries(String positionName, int minSalary, long companyId);

	Optional<Employee> findByUsername(String username);

}
