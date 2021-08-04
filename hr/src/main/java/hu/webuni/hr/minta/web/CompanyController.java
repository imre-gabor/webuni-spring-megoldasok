package hu.webuni.hr.minta.web;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
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

import hu.webuni.hr.minta.dto.CompanyDto;
import hu.webuni.hr.minta.dto.EmployeeDto;
import hu.webuni.hr.minta.mapper.CompanyMapper;
import hu.webuni.hr.minta.mapper.EmployeeMapper;
import hu.webuni.hr.minta.model.AverageSalaryByPosition;
import hu.webuni.hr.minta.model.Company;
import hu.webuni.hr.minta.repository.CompanyRepository;
import hu.webuni.hr.minta.service.CompanyService;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

	@Autowired
	CompanyService companyService;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	CompanyMapper companyMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;

	/* 1. megoldás, kézi mappelés a paraméter alapján */
	@GetMapping
	public List<CompanyDto> getCompanies(@RequestParam(required = false) Boolean full) {
		List<Company> companies = companyService.findAll();
		return mapCompanies(companies, full);
		
		
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
		return companyMapper.companyToDto(findByIdOrThrow(id));

//		CompanyDto companyDto = findByIdOrThrow(id);
//		if(full != null && full)
//			return companyDto;
//		else
//			return mapCompanyDtoWithoutEmployees(companyDto);
	}

	private Company findByIdOrThrow(long id) {
		return companyService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
		return companyMapper.companyToDto(companyService.save(companyMapper.dtoToCompany(companyDto)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto) {
		companyDto.setId(id);
		Company updatedCompany = companyService.update(companyMapper.dtoToCompany(companyDto));
		if (updatedCompany == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(companyMapper.companyToDto(updatedCompany));
	}

	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable long id) {
		companyService.delete(id);
	}
	
	@PostMapping("/{companyId}/employees")
	public CompanyDto addNewEmployee(@PathVariable long companyId, @RequestBody EmployeeDto employeeDto){
		try {
			return companyMapper.companyToDto(
					companyService.addEmployee(companyId, employeeMapper.dtoToEmployee(employeeDto))
					);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	
	@DeleteMapping("/{companyId}/employees/{employeeId}")
	public CompanyDto deleteEmployeeFromCompany(@PathVariable long companyId, @PathVariable long employeeId) {
		try {
			return companyMapper.companyToDto(
					companyService.deleteEmployee(companyId, employeeId)
					);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}
	
	@PutMapping("/{companyId}/employees")
	public CompanyDto replaceAllEmployees(@PathVariable long companyId, @RequestBody List<EmployeeDto> newEmployees){
		try {
			return companyMapper.companyToDto(
					companyService.replaceEmployees(companyId, employeeMapper.dtosToEmployees(newEmployees))
					);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
    
	@GetMapping(params = "aboveSalary")
	public List<CompanyDto> getCompaniesAboveASalary(@RequestParam int aboveSalary,
			@RequestParam(required = false) Boolean full) {
		List<Company> allCompanies = companyRepository.findByEmployeeWithSalaryHigherThan(aboveSalary);
		return mapCompanies(allCompanies, full);
	}

	private List<CompanyDto> mapCompanies(List<Company> allCompanies, Boolean full) {
		if (full == null || !full) {
			return companyMapper.companiesToSummaryDtos(allCompanies);
		} else
			return companyMapper.companiesToDtos(allCompanies);
	}

	@GetMapping(params = "aboveEmployeeNumber")
	public List<CompanyDto> getCompaniesAboveEmployeeNumber(@RequestParam int aboveEmployeeNumber,
			@RequestParam(required = false) Boolean full) {
		List<Company> filteredCompanies = companyRepository.findByEmployeeCountHigherThan(aboveEmployeeNumber);
		return mapCompanies(filteredCompanies, full);
	}
	
	@GetMapping("/{id}/salaryStats")
	public List<AverageSalaryByPosition> getSalaryStatsById(@PathVariable long id, @RequestParam(required = false) Boolean full) {
		return companyRepository.findAverageSalariesByPosition(id);
	}

}
