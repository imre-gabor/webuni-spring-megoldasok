package hu.webuni.hr.minta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.minta.model.Employee;
import hu.webuni.hr.minta.model.Position;
import hu.webuni.hr.minta.repository.PositionRepository;

@Service
public class PositionService {

	@Autowired
	PositionRepository positionRepository;

	public void setPositionForEmployee(Employee employee) {
		Position position = employee.getPosition();
		if (position != null) {
			String positionName = position.getName();
			if (positionName != null) {
				List<Position> positionsByName = positionRepository.findByName(positionName);
				if (positionsByName.isEmpty()) {
					position = positionRepository.save(new Position(positionName, null));
				} else {
					position = positionsByName.get(0);
				}
			}
		}
		employee.setPosition(position);
	}
}
