package hu.webuni.hr.minta.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hu.webuni.hr.minta.model.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeTLController {

	private List<Employee> allEmployees = new ArrayList<>();

	@GetMapping
	public String listEmployees(Map<String, Object> model) {
//	public String listEmployees(ModelMap model) {
//	public String listEmployees(Model  model) {
		model.put("employees", allEmployees);
		model.put("newEmployee", new Employee());
//		model.addAttribute("employees", allEmployees)
//			.addAttribute("newEmployee", new Employee())
//			.addAttribute(new Employee()); //by default "employee"
		
		return "employees";
	}

	@PostMapping
	public String addEmployee(Employee employee) {
		allEmployees.add(employee);
		return "redirect:/employees";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable long id) {
		allEmployees.removeIf(emp -> emp.getEmployeeId() == id);
		return "redirect:/employees";
	}
	
	@GetMapping("/{id}")
	public String editEmployee(Map<String, Object> model, @PathVariable long id) {
		model.put("employee", allEmployees
				.stream()
				.filter(e -> e.getEmployeeId() == id).findFirst().get());
		return "editEmployee";
	}
	
	@PostMapping("/updateEmployee")
	public String updateEmployee(Employee employee) {
		for(int i = 0; i< allEmployees.size(); i++) {
			if(allEmployees.get(i).getEmployeeId() == employee.getEmployeeId()) {
				allEmployees.set(i, employee);
				break;
			}
		}
		return "redirect:/employees";
	}

}
