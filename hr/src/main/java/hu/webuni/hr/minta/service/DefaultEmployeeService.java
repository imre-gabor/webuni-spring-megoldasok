package hu.webuni.hr.minta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.minta.config.HrConfigProperties;
import hu.webuni.hr.minta.model.Employee;

@Service
public class DefaultEmployeeService implements EmployeeService {
	@Autowired
	HrConfigProperties config;

	@Override
	public int getPayRaisePercent(Employee employee) {
		return config.getSalary().getDef().getPercent();
	}

}
