package hu.webuni.hr.minta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.hr.minta.service.EmployeeService;
import hu.webuni.hr.minta.service.SmartEmployeeService;

@Configuration
@Profile("smart")
public class SmartSalaryConfiguration {

	@Bean
	public EmployeeService employeeService() {
		return new SmartEmployeeService();
	}
}
