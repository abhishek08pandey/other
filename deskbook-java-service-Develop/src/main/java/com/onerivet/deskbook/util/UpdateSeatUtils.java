package com.onerivet.deskbook.util;

import java.time.LocalDateTime;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.onerivet.deskbook.exception.ResourceNotFoundException;
import com.onerivet.deskbook.models.entity.ColumnDetails;
import com.onerivet.deskbook.models.entity.Employee;
import com.onerivet.deskbook.models.entity.SeatConfiguration;
import com.onerivet.deskbook.models.entity.SeatNumber;
import com.onerivet.deskbook.models.payload.UpdateProfileDto;
import com.onerivet.deskbook.repository.DesignationRepo;
import com.onerivet.deskbook.repository.ModeOfWorkRepo;
import com.onerivet.deskbook.repository.SeatConfigurationRepo;
import com.onerivet.deskbook.repository.SeatNumberRepo;

@Component
public class UpdateSeatUtils {

	@Autowired
	private DesignationRepo designationRepo;

	@Autowired
	private ModeOfWorkRepo modeOfWorkRepo;

	@Autowired
	private SeatNumberRepo seatNumberRepo;

	@Autowired
	private SeatConfigurationRepo seatConfigurationRepo;

	@Autowired
	private ImageUtils imageUtils;

	@Value("${image.upload.path}")
	String path;
	
	static final Logger logger = LoggerFactory.getLogger(UpdateSeatUtils.class);

	public void checkExceptions(Employee employee, UpdateProfileDto newEmployeeDto) {

////		if (newEmployeeDto.getModeOfWork() == 2 && (newEmployeeDto.getFloor() != 1 && newEmployeeDto.getFloor() != 6)) {
//		if (newEmployeeDto.getModeOfWork() == 2 && (newEmployeeDto.getFloor() != null)) {
//			throw new IllegalArgumentException("Enter Valid Floor");
//		}
//
//		if (newEmployeeDto.getModeOfWork() == 2
//				&& (newEmployeeDto.getColumn() != null)) {
//			throw new IllegalArgumentException("Enter Valid Column");
//		}
//
//		if (newEmployeeDto.getModeOfWork() == 2 && (newEmployeeDto.getSeat() != null)) {
//			throw new IllegalArgumentException("Enter Valid Seat");
//		}
//
//		if ((newEmployeeDto.getModeOfWork() == 3 || newEmployeeDto.getModeOfWork() == 2)
//				&& (newEmployeeDto.getWorkingDays() != null && newEmployeeDto.getWorkingDays().size() != 0)) {
//			throw new IllegalArgumentException("You cannot select working day");
//		}
//
//		if (newEmployeeDto.getModeOfWork() == 1
//				&& (newEmployeeDto.getWorkingDays() == null || newEmployeeDto.getWorkingDays().size() == 0)) {
//			throw new IllegalArgumentException("Please select Days");
//		}
//
//		if (newEmployeeDto.getModeOfWork() == 1 && newEmployeeDto.getWorkingDays().size() >= 5)
//			throw new IllegalArgumentException("You can select Maximum 4 days");

	}

	public Employee getUpdatedEmployee(Employee employee, UpdateProfileDto newEmployeeDto) throws Exception {

		if (newEmployeeDto.getProfilePictureFileString() != null) {
			String fileExtension = imageUtils.base64ToFile(newEmployeeDto.getProfilePictureFileString(),
					newEmployeeDto.getFirstName(), employee.getId());
			employee.setProfilePictureFileName(employee.getId() + fileExtension);
			employee.setProfilePictureFilePath(
					path +  employee.getId() + fileExtension);

		} else {
			employee.setProfilePictureFileName(null);
			employee.setProfilePictureFilePath(null);
		}

		employee.setFirstName(newEmployeeDto.getFirstName());
		employee.setLastName(newEmployeeDto.getLastName());
		employee.setPhoneNumber(newEmployeeDto.getPhoneNumber());

		employee.setDesignation(this.designationRepo.findById(newEmployeeDto.getDesignation())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Designation With id " + newEmployeeDto.getDesignation() + " not found.")));
		employee.setModeOfWork(this.modeOfWorkRepo.findById(newEmployeeDto.getModeOfWork())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Mode of work With id " + newEmployeeDto.getModeOfWork() + " not found.")));

		employee.setModifiedBy(employee);
		employee.setModifiedDate(LocalDateTime.now());

		return employee;
	}

	public SeatConfiguration saveSeat(Employee employee, UpdateProfileDto newEmployeeDto) {

		SeatNumber seatNumber = this.seatNumberRepo.findById(newEmployeeDto.getSeat()).orElseThrow(
				() -> new ResourceNotFoundException("Seat With id " + newEmployeeDto.getSeat() + " not found."));

		seatNumber.setBooked(true);

//		if (employee.getModeOfWork().getId() == 1 || employee.getModeOfWork().getId() == 3) {
//			if (newEmployeeDto.getFloor() == 1 || newEmployeeDto.getFloor() == 6)
//				throw new IllegalArgumentException("Enter valid seat information");
//			if (newEmployeeDto.getColumn() == 1 || newEmployeeDto.getColumn() == 42)
//				throw new IllegalArgumentException("Enter valid seat information");
//			if (newEmployeeDto.getSeat() == 1 || newEmployeeDto.getSeat() == 402)
//				throw new IllegalArgumentException("Enter valid seat information");
//		}

		Map<String, ColumnDetails> map = seatNumberRepo.findColumnFloorCityBySeat(seatNumber.getId());

		ColumnDetails columnDetails = map.get("Column");
		System.out.println(columnDetails);
		if (columnDetails != null) {
			if (columnDetails.getFloor().getCity().getId() != newEmployeeDto.getCity()) {
				logger.error("Enter valid seat information");
				throw new IllegalArgumentException("Enter valid seat information");
			}
			if (columnDetails.getFloor().getId() != newEmployeeDto.getFloor()) {
				logger.error("Enter valid seat information");
				throw new IllegalArgumentException("Enter valid seat information");
			}

			if (columnDetails.getId() != newEmployeeDto.getColumn()) {
				logger.error("Enter valid seat information");
				throw new IllegalArgumentException("Enter Valid seat information");
			}
		}

		SeatConfiguration employeeSeat = this.seatConfigurationRepo.findByEmployeeAndDeletedByNull(employee);
		if (employeeSeat == null) {
			employeeSeat = new SeatConfiguration();
		}

		SeatConfiguration bookedSeat = this.seatConfigurationRepo.findBySeatNumberAndDeletedByNull(seatNumber);

		if (bookedSeat != null && !bookedSeat.getEmployee().getId().equals(employee.getId())) {
			logger.error("Already Booked");
			throw new IllegalArgumentException("Already Booked");
		}
			

		employeeSeat.setCreatedBy(employee);
		employeeSeat.setEmployee(employee);
		employeeSeat.setModifiedBy(employee);
		employeeSeat.setModifiedDate(LocalDateTime.now());
		employeeSeat.setSeatNumber(seatNumber);

		return employeeSeat;
	}

}
