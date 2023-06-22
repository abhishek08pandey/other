package com.onerivet.deskbook.util;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onerivet.deskbook.models.entity.Employee;
import com.onerivet.deskbook.models.entity.EmployeeWorkingDays;
import com.onerivet.deskbook.models.entity.SeatConfiguration;
import com.onerivet.deskbook.models.entity.WorkingDay;
import com.onerivet.deskbook.models.payload.CityDto;
import com.onerivet.deskbook.models.payload.ColumnDetailsDto;
import com.onerivet.deskbook.models.payload.DesignationDto;
import com.onerivet.deskbook.models.payload.FloorDto;
import com.onerivet.deskbook.models.payload.ModeOfWorkDto;
import com.onerivet.deskbook.models.payload.ProfileViewDto;
import com.onerivet.deskbook.models.payload.SeatNumberDto;
import com.onerivet.deskbook.models.payload.WorkingDayDto;
import com.onerivet.deskbook.repository.EmployeeWorkingDaysRepo;

@Component
public class ProfileMapper {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ImageUtils imageUtils;

	@Autowired
	private EmployeeWorkingDaysRepo employeeWorkingDaysRepo;

	public ProfileViewDto getProfile(Employee employee, SeatConfiguration seatConfiguration) throws Exception {
		ProfileViewDto profile = new ProfileViewDto();
		Set<WorkingDayDto> days = new LinkedHashSet<WorkingDayDto>();

		if (employee.getProfilePictureFileName() != null && employee.getProfilePictureFilePath() != null) {
			profile.setProfilePictureFileString(imageUtils.encodeImage(employee.getProfilePictureFilePath()));
		}

		profile.setEmailId(employee.getEmailId());
		profile.setFirstName(employee.getFirstName());
		profile.setLastName(employee.getLastName());
		profile.setPhoneNumber(employee.getPhoneNumber());

		if (employee.getDesignation() != null)
			profile.setDesignation(this.modelMapper.map(employee.getDesignation(), DesignationDto.class));

		if (seatConfiguration != null && employee.getModeOfWork().getId() != 2) {
			profile.setCity(this.modelMapper.map(seatConfiguration.getSeatNumber().getColumn().getFloor().getCity(),
					CityDto.class));
			profile.setColumn(
					this.modelMapper.map(seatConfiguration.getSeatNumber().getColumn(), ColumnDetailsDto.class));
			profile.setFloor(
					this.modelMapper.map(seatConfiguration.getSeatNumber().getColumn().getFloor(), FloorDto.class));
			profile.setSeat(this.modelMapper.map(seatConfiguration.getSeatNumber(), SeatNumberDto.class));
			profile.getSeat().setBooked(true);
		} else {
			profile.setCity(null);
			profile.setColumn(null);
			profile.setFloor(null);
			profile.setSeat(null);
		}

		if (employee.getModeOfWork() != null) {
			profile.setModeOfWork(this.modelMapper.map(employee.getModeOfWork(), ModeOfWorkDto.class));

			if (employee.getModeOfWork().getId() == 1) {
				List<EmployeeWorkingDays> workingDays = this.employeeWorkingDaysRepo.findByEmployee(employee);
				for (EmployeeWorkingDays day : workingDays) {
					if (day.getDeletedBy() == null)
						days.add(this.modelMapper.map(day.getDay(), WorkingDayDto.class));
				}

			} else if (employee.getModeOfWork().getId() == 3) {
				days.add(this.modelMapper.map(new WorkingDay(1, "Monday"), WorkingDayDto.class));
				days.add(this.modelMapper.map(new WorkingDay(2, "Tuesday"), WorkingDayDto.class));
				days.add(this.modelMapper.map(new WorkingDay(3, "Wednesday"), WorkingDayDto.class));
				days.add(this.modelMapper.map(new WorkingDay(4, "Thursday"), WorkingDayDto.class));
				days.add(this.modelMapper.map(new WorkingDay(5, "Friday"), WorkingDayDto.class));
			}

			profile.setDays(days);
		}
		
		profile.setActive(employee.isActive());

		return profile;
	}
}
