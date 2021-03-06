package hu.webuni.hr.minta.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@NamedEntityGraph(name="Company.full",
attributeNodes = {
		@NamedAttributeNode(value = "employees", subgraph = "employeesGraph")
},
subgraphs = {
		@NamedSubgraph(name="employeesGraph", attributeNodes = {
				@NamedAttributeNode("position")
		})
})
public class Company {
	
	@Id
	@GeneratedValue
	private Long id;
	private int registrationNumber;
	private String name;
	private String address;
	
	@OneToMany(mappedBy = "company")
	private List<Employee> employees;
	
	@ManyToOne
	private CompanyType companyType;
	
	public Company() {
		
	}

	public Company(Long id, int registrationNumber, String name, String adress, List<Employee> employees) {
		this.id = id;
		this.registrationNumber = registrationNumber;
		this.name = name;
		this.address = adress;
		this.employees = employees;
	}

	public int getRegistrationNumber() {
		return registrationNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRegistrationNumber(int registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void addEmployee(Employee employee) {
		if(this.employees == null)
			this.employees = new ArrayList<>();
		this.employees.add(employee);
		employee.setCompany(this);
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}	
}
