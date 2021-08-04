package hu.webuni.hr.minta.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Position {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private Qualification qualification;
	
//	@OneToMany(mappedBy = "position")
//	private List<Employee> employees;
	
	
	public Position() {
	}
	
	public Position(String name, Qualification qualification) {
		super();
		this.name = name;
		this.qualification = qualification;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Qualification getQualification() {
		return qualification;
	}
	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}
	
}
