package hu.webuni.hr.minta.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import hu.webuni.hr.minta.dto.CompanyDto;
import hu.webuni.hr.minta.dto.EmployeeDto;
import hu.webuni.hr.minta.model.Company;
import hu.webuni.hr.minta.model.Employee;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

	CompanyDto companyToDto(Company company);

	@Mapping(target = "employees" , ignore = true)
	@Named("summary")
	CompanyDto companyToSummaryDto(Company company);
	
	List<CompanyDto> companiesToDtos(List<Company> company);
	
	@IterableMapping(qualifiedByName = "summary")
	List<CompanyDto> companiesToSummaryDtos(List<Company> company);
	

	@Mapping(target = "id", source = "employeeId")
	@Mapping(target = "title", source = "position.name")
	@Mapping(target = "entryDate", source = "dateOfStartWork")
	@Mapping(target = "company", ignore = true)
	EmployeeDto employeeToDto(Employee employee);

	@Mapping(source = "id", target = "employeeId")
	@Mapping(source = "title", target = "position.name")
	@Mapping(source = "entryDate", target = "dateOfStartWork")
	@Mapping(target = "company", ignore = true)
	Employee dtoToEmployee(EmployeeDto employeeDto);

	Company dtoToCompany(CompanyDto companyDto);
	
	
}
