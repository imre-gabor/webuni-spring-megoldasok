package hu.webuni.hr.minta.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.webuni.hr.minta.dto.EmployeeDto;
import hu.webuni.hr.minta.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
	
	List<EmployeeDto> employeesToDtos(List<Employee> employees);

	@Mapping(target = "id", source = "employeeId")
	@Mapping(target = "title", source = "position.name")
	@Mapping(target = "entryDate", source = "dateOfStartWork")
	EmployeeDto employeeToDto(Employee employee);

	@Mapping(source = "id", target = "employeeId")
	@Mapping(source = "title", target = "position.name")
	@Mapping(source = "entryDate", target = "dateOfStartWork")
	Employee dtoToEmployee(EmployeeDto employeeDto);

	List<Employee> dtosToEmployees(List<EmployeeDto> employees);

}
