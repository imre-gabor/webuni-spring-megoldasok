package hu.webuni.hr.minta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.minta.model.Position;
import hu.webuni.hr.minta.model.PositionDetailsByCompany;

public interface PositionDetailsByCompanyRepository extends JpaRepository<PositionDetailsByCompany, Long> {

	List<PositionDetailsByCompany> findByPositionNameAndCompanyId(String positionName, long companyId);

}
