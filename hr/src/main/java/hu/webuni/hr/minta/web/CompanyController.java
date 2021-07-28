package hu.webuni.hr.minta.web;

import java.util.List;

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

import hu.webuni.hr.minta.dto.CompanyDto;
import hu.webuni.hr.minta.dto.EmployeeDto;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {


	/* 1. megoldás, kézi mappelés a paraméter alapján */
	@GetMapping
	public List<CompanyDto> getCompanies(@RequestParam(required = false) Boolean full) {
		return null;

		
//		if(full != null && full)
//			return new ArrayList<>(companies.values());
//		else
//			return companies.values()
//			.stream()
//			.map(this::mapCompanyDtoWithoutEmployees)
//			.collect(Collectors.toList());
			
	}
	
	/* 2. megoldás, @JsonView annotációval */
//	@GetMapping
//	@JsonView(Views.BaseData.class)
//	public List<CompanyDto> getCompanies() {
//		return new ArrayList<>(companies.values());
//	}
//	
//	@GetMapping(params = "full=true")
//	public List<CompanyDto> getCompaniesFull() {
//		return new ArrayList<>(companies.values());
//	}
	

	@GetMapping("/{id}")
	public CompanyDto getById(@PathVariable long id, @RequestParam(required = false) Boolean full) {
		return null;

//		CompanyDto companyDto = findByIdOrThrow(id);
//		if(full != null && full)
//			return companyDto;
//		else
//			return mapCompanyDtoWithoutEmployees(companyDto);
	}

	@PostMapping
	public CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
		return companyDto;
//		return companyMapper.companyToDto(companyService.save(companyMapper.dtoToCompany(companyDto)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto) {
		return null;
//		companyDto.setId(id);
//		Company updatedCompany = companyService.update(companyMapper.dtoToCompany(companyDto));
//		if (updatedCompany == null) {
//			return ResponseEntity.notFound().build();
//		}
//
//		return ResponseEntity.ok(companyMapper.companyToDto(updatedCompany));
	}

	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable long id) {
//		companyService.delete(id);
	}
	
	@PostMapping("/{companyId}/employees")
	public CompanyDto addNewEmployee(@PathVariable long companyId, @RequestBody EmployeeDto employeeDto){
		return null;
//		try {
//			return companyMapper.companyToDto(
//					companyService.addEmployee(id, employeeMapper.dtoToEmployee(employeeDto))
//					);
//		} catch (NoSuchElementException e) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//		}
	}
//
//	private CompanyDto findByIdOrThrow(long companyId) {
//		CompanyDto companyDto = companies.get(companyId);
//		if(companyDto == null)
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//		return companyDto;
//	}
	
	@DeleteMapping("/{companyId}/employees/{employeeId}")
	public CompanyDto deleteEmployeeFromCompany(@PathVariable long companyId, @PathVariable long employeeId) {
		return null;
//		try {
//			return companyMapper.companyToDto(
//					companyService.deleteEmployee(id, employeeId)
//					);
//		} catch (NoSuchElementException e) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//		}

	}
	
	@PutMapping("/{companyId}/employees")
	public CompanyDto replaceAllEmployees(@PathVariable long companyId, @RequestBody List<EmployeeDto> newEmployees){
		return null;
//		try {
//			return companyMapper.companyToDto(
//					companyService.replaceEmployees(id, employeeMapper.dtosToEmployees(employees))
//					);
//		} catch (NoSuchElementException e) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//		}
	}

}
