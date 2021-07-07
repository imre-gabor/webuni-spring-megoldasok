package hu.webuni.hr.minta.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.minta.config.HrConfigProperties;
import hu.webuni.hr.minta.config.HrConfigProperties.Smart;
import hu.webuni.hr.minta.model.Employee;

@Service
public class SmartEmployeeService implements EmployeeService {

	@Autowired
	HrConfigProperties config;

	@Override
	public int getPayRaisePercent(Employee employee) {
		
		double yearsWorked = ChronoUnit.DAYS.between(employee.getDateOfStartWork(), LocalDateTime.now()) / 365.0;
		
		Smart smartConfig = config.getSalary().getSmart();
		if(yearsWorked > smartConfig.getLimit3())
			return smartConfig.getPercent3();
		
		if(yearsWorked > smartConfig.getLimit2())
			return smartConfig.getPercent2();
		
		if(yearsWorked > smartConfig.getLimit1())
			return smartConfig.getPercent1();
		
		return 0;
	}

}
