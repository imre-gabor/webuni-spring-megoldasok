package hu.webuni.hr.minta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.hr.minta.service.DefaultEmployeeService;
import hu.webuni.hr.minta.service.EmployeeService;

@Configuration
@Profile("!smart")
public class DefaultSalaryConfiguration {

	@Bean
	public EmployeeService employeeService() {
		return new DefaultEmployeeService();
	}
}
