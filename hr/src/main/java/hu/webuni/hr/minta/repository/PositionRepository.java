package hu.webuni.hr.minta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.minta.model.AverageSalaryByPosition;
import hu.webuni.hr.minta.model.Company;
import hu.webuni.hr.minta.model.Position;

public interface PositionRepository extends JpaRepository<Position, Integer> {

	public List<Position> findByName(String name);
}
