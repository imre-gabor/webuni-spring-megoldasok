package hu.webuni.hr.minta.mapper;

import java.util.List;

import javax.validation.Valid;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.webuni.hr.minta.dto.HolidayRequestDto;
import hu.webuni.hr.minta.model.HolidayRequest;


@Mapper(componentModel = "spring")
public interface HolidayRequestMapper {

	
	List<HolidayRequestDto> holidayRequestsToDtos(List<HolidayRequest> holidayRequests);	
	
	@Mapping(source = "employee.employeeId", target = "employeeId")
	@Mapping(source = "approver.employeeId", target = "approverId")	
	HolidayRequestDto holidayRequestToDto(HolidayRequest holidayRequest);

	@Mapping(target = "employee", ignore = true)
	@Mapping(target = "approver", ignore = true)
	HolidayRequest dtoToHolidayRequest(@Valid HolidayRequestDto holidayRequestDto);

	List<HolidayRequest> dtosToHolidayRequests(List<HolidayRequestDto> holidayRequestDtos);
	

}
