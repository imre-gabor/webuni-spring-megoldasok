package hu.webuni.hr.minta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hu.webuni.hr.minta.model.HolidayRequest;

public interface HolidayRequestRepository extends JpaRepository<HolidayRequest, Long>, JpaSpecificationExecutor<HolidayRequest> {
}
