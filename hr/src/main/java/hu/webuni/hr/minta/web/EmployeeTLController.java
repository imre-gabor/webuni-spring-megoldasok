//package hu.webuni.hr.minta.web;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import hu.webuni.hr.minta.model.Employee;
//
//@Controller
//public class EmployeeTLController {
//
//	private List<Employee> allEmployees = new ArrayList<>();
//	
//	{
//		allEmployees.add(new Employee(1L, "Kis Gábor", "osztályvezető", 100000, LocalDateTime.of(2012, 1, 1, 8, 0, 0)));
//	}
//
//	@GetMapping("/employees")
//	public String listEmployees(Map<String, Object> model) {
//		model.put("employees", allEmployees);
//		model.put("newEmployee", new Employee());
//		return "employees";
//	}
//
//	@PostMapping("/employees")
//	public String addEmployee(Employee employee) {
//		allEmployees.add(employee);
//		return "redirect:employees";
//	}
//
//}
