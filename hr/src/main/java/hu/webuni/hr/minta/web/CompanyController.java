package hu.webuni.hr.minta.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import hu.webuni.hr.minta.dto.CompanyDto;
import hu.webuni.hr.minta.dto.EmployeeDto;
import hu.webuni.hr.minta.dto.Views;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

	private Map<Long, CompanyDto> companies = new HashMap<>();

	/* 1. megoldás, kézi mappelés a paraméter alapján
	@GetMapping
	public List<CompanyDto> getCompanies(@RequestParam(required = false) Boolean full) {
		
		if(full != null && full)
			return new ArrayList<>(companies.values());
		else
			return companies.values()
			.stream()
			.map(this::mapCompanyDtoWithoutEmployees)
			.collect(Collectors.toList());
			
	}*/
	
	/* 2. megoldás, @JsonView annotációval */
	@GetMapping
	@JsonView(Views.BaseData.class)
	public List<CompanyDto> getCompanies() {
		return new ArrayList<>(companies.values());
	}
	
	@GetMapping(params = "full=true")
	public List<CompanyDto> getCompaniesFull() {
		return new ArrayList<>(companies.values());
	}
	
	private CompanyDto mapCompanyDtoWithoutEmployees(CompanyDto c) {
		return new CompanyDto(c.getId(), c.getRegistrationNumber(), c.getName(), c.getAddress(), null);
	}

	@GetMapping("/{id}")
	public CompanyDto getById(@PathVariable long id, @RequestParam(required = false) Boolean full) {
		CompanyDto companyDto = findByIdOrThrow(id);
		if(full != null && full)
			return companyDto;
		else
			return mapCompanyDtoWithoutEmployees(companyDto);
	}

	@PostMapping
	public CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
		companies.put(companyDto.getId(), companyDto);
		return companyDto;
	}

	@PutMapping("/{id}")
	public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto) {
		if (!companies.containsKey(id)) {
			return ResponseEntity.notFound().build();
		}

		companyDto.setId(id);
		companies.put(id, companyDto);
		return ResponseEntity.ok(companyDto);
	}

	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable long id) {
		companies.remove(id);
	}
	
	@PostMapping("/{companyId}/employees")
	public CompanyDto addNewEmployee(@PathVariable long companyId, @RequestBody EmployeeDto employeeDto){
		CompanyDto companyDto = findByIdOrThrow(companyId);
		
		companyDto.getEmployees().add(employeeDto);
		return companyDto;
	}

	private CompanyDto findByIdOrThrow(long companyId) {
		CompanyDto companyDto = companies.get(companyId);
		if(companyDto == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return companyDto;
	}
	
	@DeleteMapping("/{companyId}/employees/{employeeId}")
	public CompanyDto deleteEmployeeFromCompany(@PathVariable long companyId, @PathVariable long employeeId) {
		CompanyDto companyDto = findByIdOrThrow(companyId);
		companyDto.getEmployees().removeIf(emp -> emp.getId() == employeeId);
		return companyDto;
	}
	
	@PutMapping("/{companyId}/employees")
	public CompanyDto replaceAllEmployees(@PathVariable long companyId, @RequestBody List<EmployeeDto> newEmployees){
		CompanyDto companyDto = findByIdOrThrow(companyId);
		companyDto.setEmployees(newEmployees);
		return companyDto;
	}
}
