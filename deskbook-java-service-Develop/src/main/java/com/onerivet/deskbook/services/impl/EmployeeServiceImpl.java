package com.onerivet.deskbook.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onerivet.deskbook.exception.ResourceNotFoundException;
import com.onerivet.deskbook.models.entity.Employee;
import com.onerivet.deskbook.models.entity.EmployeeWorkingDays;
import com.onerivet.deskbook.models.entity.SeatConfiguration;
import com.onerivet.deskbook.models.payload.EmployeeDto;
import com.onerivet.deskbook.models.payload.ProfileUpdatedOrNot;
import com.onerivet.deskbook.models.payload.ProfileViewDto;
import com.onerivet.deskbook.models.payload.UpdateProfileDto;
import com.onerivet.deskbook.repository.EmployeeRepo;
import com.onerivet.deskbook.repository.EmployeeWorkingDaysRepo;
import com.onerivet.deskbook.repository.SeatConfigurationRepo;
import com.onerivet.deskbook.repository.WorkingDaysRepo;
import com.onerivet.deskbook.services.EmployeeService;
import com.onerivet.deskbook.util.ImageUtils;
import com.onerivet.deskbook.util.ProfileMapper;
import com.onerivet.deskbook.util.UpdateSeatUtils;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private SeatConfigurationRepo seatConfigurationRepo;

	@Autowired
	private EmployeeWorkingDaysRepo employeeWorkingDaysRepo;

	@Autowired
	private WorkingDaysRepo workingDaysRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProfileMapper profileMapper;

	@Autowired
	private UpdateSeatUtils updateSeatUtils;

	@Autowired
	private ImageUtils imageUtils;

	final static Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Value("${image.upload.path}")
	private String path;

	public EmployeeServiceImpl(EmployeeRepo employeeRepo, SeatConfigurationRepo seatConfigurationRepo,
			EmployeeWorkingDaysRepo employeeWorkingDaysRepo, WorkingDaysRepo workingDaysRepo, ModelMapper modelMapper,
			ProfileMapper profileMapper, UpdateSeatUtils updateSeatUtils) {
		super();
		this.employeeRepo = employeeRepo;
		this.seatConfigurationRepo = seatConfigurationRepo;
		this.employeeWorkingDaysRepo = employeeWorkingDaysRepo;
		this.workingDaysRepo = workingDaysRepo;
		this.modelMapper = modelMapper;
		this.profileMapper = profileMapper;
		this.updateSeatUtils = updateSeatUtils;

	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		logger.info("[getAllEmployees()] started");
		logger.info("[getAllEmployees()] ended");
		return this.employeeRepo.findAll().stream().map((employee) -> this.modelMapper.map(employee, EmployeeDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ProfileViewDto getEmployeeById(String id) throws Exception {
		logger.info("[getEmployeeById()] started");
		Employee employee = this.employeeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee with id " + id + " not found."));

		SeatConfiguration seatConfiguration = seatConfigurationRepo.findByEmployeeAndDeletedByNull(employee);
		logger.info("[getEmployeeById()] ended");

		return profileMapper.getProfile(employee, seatConfiguration);
	}

	@Override
	public ProfileViewDto updateEmpById(String id, UpdateProfileDto newEmployeeDto) throws Exception {

		logger.info("[updateEmpById()] started");

		Employee employee = this.employeeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee With id " + id + " not found."));
//		if(!employee.isActive()) {
//			logger.error("You are an inactive user. Please contact your administrator.");
//			throw new IllegalArgumentException("You are an inactive user. Please contact your administrator.");
//		}
		SeatConfiguration savedSeatConfiguration = null;

//		updateSeatUtils.checkExceptions(employee, newEmployeeDto);

		employee = updateSeatUtils.getUpdatedEmployee(employee, newEmployeeDto);

		if (employee.getModeOfWork().getId() != 2) {
			SeatConfiguration employeeSeat = updateSeatUtils.saveSeat(employee, newEmployeeDto);
			savedSeatConfiguration = seatConfigurationRepo.save(employeeSeat);
			List<EmployeeWorkingDays> employeeWorkingDays = employeeWorkingDaysRepo.findByEmployee(employee);

			for (EmployeeWorkingDays day : employeeWorkingDays) {
				day.setDeletedBy(employee);
				day.setDeletedDate(LocalDateTime.now());
				employeeWorkingDaysRepo.save(day);
			}

			if (employee.getModeOfWork().getId() == 1) {

				for (int i : newEmployeeDto.getWorkingDays()) {
					employeeWorkingDaysRepo.save(new EmployeeWorkingDays(employee,
							this.workingDaysRepo.findById(i).orElseThrow(() -> new ResourceNotFoundException("WorkingDay not found.")), employee, employee, LocalDateTime.now()));
				}
			}
		} else {
			SeatConfiguration seatConfiguration = seatConfigurationRepo.findByEmployeeAndDeletedByNull(employee);

			if (seatConfiguration != null) {
				seatConfiguration.setDeletedBy(employee);
				seatConfiguration.setDeletedDate(LocalDateTime.now());
				savedSeatConfiguration = seatConfigurationRepo.save(seatConfiguration);
			}

			List<EmployeeWorkingDays> employeeWorkingDays = employeeWorkingDaysRepo.findByEmployee(employee);

			for (EmployeeWorkingDays day : employeeWorkingDays) {
				day.setDeletedBy(employee);
				day.setDeletedDate(LocalDateTime.now());
				employeeWorkingDaysRepo.save(day);
			}
		}

		Employee savedEmployee = this.employeeRepo.save(employee);
		logger.info("[updateEmpById()] ended");

		return profileMapper.getProfile(savedEmployee, savedSeatConfiguration);

	}

	@Override
	public ProfileUpdatedOrNot isProfileUpdated(String id) throws Exception {
		logger.info("[isProfileUpdated()] started");

		Employee employee = this.employeeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee with id " + id + " not found."));

		ProfileUpdatedOrNot updatedOrNot = new ProfileUpdatedOrNot();

		if (employee.getModeOfWork() != null) {
			updatedOrNot.setUpdated(true);
			if (employee.getProfilePictureFileName() != null && employee.getProfilePictureFilePath() != null) {
				updatedOrNot.setProfilePictureFileString(imageUtils.encodeImage(employee.getProfilePictureFilePath()));
			}
		}

		updatedOrNot.setActive(employee.isActive());
		logger.info("[isProfileUpdated()] ended");
		return updatedOrNot;

	}

}
