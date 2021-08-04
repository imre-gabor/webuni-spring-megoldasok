package hu.webuni.hr.minta;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.webuni.hr.minta.config.HrConfigProperties;
import hu.webuni.hr.minta.config.HrConfigProperties.Smart;
import hu.webuni.hr.minta.model.Employee;
import hu.webuni.hr.minta.service.InitDbService;
import hu.webuni.hr.minta.service.SalaryService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

	@Autowired
	SalaryService salaryService;
	
	@Autowired
	HrConfigProperties config;
	
	@Autowired
	InitDbService initDbService;

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {
		Smart smartConfig = config.getSalary().getSmart();
		for (Double limit : 
				smartConfig.getLimits().keySet()
			/*Arrays.asList(smartConfig.getLimit1(), smartConfig.getLimit2(), smartConfig.getLimit3())*/) {
			
			int origSalary = 100;
			LocalDateTime limitDay = LocalDateTime.now().minusDays((long)(limit*365));
			Employee e1 = new Employee(1L, "Nagy Péter", "fejlesztő", origSalary, limitDay.plusDays(1));
			Employee e2 = new Employee(2L, "Kis Gábor", "projektmenedzser", origSalary, limitDay.minusDays(1));

			salaryService.setNewSalary(e1);
			salaryService.setNewSalary(e2);

			System.out.format("1 nappal a %.2f éves határ előtt az új fizetés %d%n", limit, e1.getSalary());
			System.out.format("1 nappal a %.2f éves határ után az új fizetés %d%n", limit, e2.getSalary());
		}
		initDbService.initDb();
		
	}


}
