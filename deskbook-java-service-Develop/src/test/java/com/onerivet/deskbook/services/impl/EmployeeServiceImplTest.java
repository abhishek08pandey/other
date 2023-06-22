package com.onerivet.deskbook.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.onerivet.deskbook.models.entity.City;
import com.onerivet.deskbook.models.entity.ColumnDetails;
import com.onerivet.deskbook.models.entity.Designation;
import com.onerivet.deskbook.models.entity.Employee;
import com.onerivet.deskbook.models.entity.EmployeeWorkingDays;
import com.onerivet.deskbook.models.entity.Floor;
import com.onerivet.deskbook.models.entity.ModeOfWork;
import com.onerivet.deskbook.models.entity.SeatConfiguration;
import com.onerivet.deskbook.models.entity.SeatNumber;
import com.onerivet.deskbook.models.entity.WorkingDay;
import com.onerivet.deskbook.models.payload.CityDto;
import com.onerivet.deskbook.models.payload.ColumnDetailsDto;
import com.onerivet.deskbook.models.payload.DesignationDto;
import com.onerivet.deskbook.models.payload.EmployeeDto;
import com.onerivet.deskbook.models.payload.FloorDto;
import com.onerivet.deskbook.models.payload.ModeOfWorkDto;
import com.onerivet.deskbook.models.payload.ProfileViewDto;
import com.onerivet.deskbook.models.payload.SeatNumberDto;
import com.onerivet.deskbook.models.payload.UpdateProfileDto;
import com.onerivet.deskbook.models.payload.WorkingDayDto;
import com.onerivet.deskbook.repository.EmployeeRepo;
import com.onerivet.deskbook.repository.EmployeeWorkingDaysRepo;
import com.onerivet.deskbook.repository.SeatConfigurationRepo;
import com.onerivet.deskbook.repository.WorkingDaysRepo;
import com.onerivet.deskbook.util.ProfileMapper;
import com.onerivet.deskbook.util.UpdateSeatUtils;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

	@InjectMocks
	private EmployeeServiceImpl employeeServiceImpl;

	@Mock
	private EmployeeRepo employeeRepo;

	private Employee employee;

	private SeatConfiguration seatConfiguration;

	private Designation designation;

	private ModeOfWork modeOfWork;

	private City city;

	private Floor floor;

	private ColumnDetails column;

	private SeatNumber seat;

	private Set<WorkingDay> days = new HashSet<>();

	@Mock
	private SeatConfigurationRepo seatConfigurationRepo;

	@Mock
	private EmployeeWorkingDaysRepo employeeWorkingDaysRepo;

	@Mock
	private WorkingDaysRepo workingDaysRepo;

	@Mock
	private ModelMapper modelMapper;

	@Mock
	private ProfileMapper profileMapper;

	private ProfileViewDto profile;

	@Mock
	private UpdateSeatUtils updateSeatUtils;

	@BeforeEach
	void setUp() throws Exception {
		employeeServiceImpl = new EmployeeServiceImpl(this.employeeRepo, seatConfigurationRepo, employeeWorkingDaysRepo,
				workingDaysRepo, modelMapper, profileMapper, updateSeatUtils);
		Mockito.mock(Employee.class);
		Mockito.mock(SeatConfiguration.class);
		Mockito.mock(Designation.class);
		Mockito.mock(ModeOfWork.class);
		Mockito.mock(City.class);
		Mockito.mock(Floor.class);
		Mockito.mock(ColumnDetails.class);
		Mockito.mock(SeatNumber.class);
	}

	@Test
	void testGetAllEmployees_WithEmployees_ReturnsEmployeeDtoList() {

		mock(Employee.class);

		mock(EmployeeServiceImpl.class);

		mock(EmployeeDto.class);

		Employee employee1 = Employee.builder().id("ygjvb76-jhgj6-hk6fgh").emailId("drashti@gmail.com")
				.firstName("drashti").lastName("balsara").phoneNumber("7778889994").profilePictureFileName("null")
				.profilePictureFilePath("null").modeOfWork(null).designation(null).build();

		Employee employee2 = Employee.builder().id("ygjvb76-jhgj6-hkh743").emailId("diya@gmail.com").firstName("diya")
				.lastName("balsara").phoneNumber("7778889554").profilePictureFileName("null")
				.profilePictureFilePath("null").modeOfWork(null).designation(null).build();

		List<Employee> employees = Arrays.asList(employee1, employee2);

		EmployeeDto e1 = new EmployeeDto("ygjvb76-jhgj6-hk6fgh", "drashti@gmail.com", "drashti", "balsara",
				"7778889994", "null", "null", null, null, true);
		EmployeeDto e2 = new EmployeeDto("ygjvb76-jhgj6-hkh743", "diya@gmail.com", "diya", "balsara", "7778889554",
				"null", "null", null, null, true);

		List<EmployeeDto> employeeDtos = Arrays.asList(e1, e2);

//		verify(this.employeeRepo.findAll(), times(1)).equals(employeeDtos);
		assertThat(employees.get(0).getId()).isEqualTo(employeeDtos.get(0).getId());
		assertThat(employees.get(0).getEmailId()).isEqualTo(employeeDtos.get(0).getEmailId());
		assertThat(employees.get(0).getFirstName()).isEqualTo(employeeDtos.get(0).getFirstName());
		assertThat(employees.get(0).getLastName()).isEqualTo(employeeDtos.get(0).getLastName());
		assertThat(employees.get(0).getPhoneNumber()).isEqualTo(employeeDtos.get(0).getPhoneNumber());
		assertThat(employees.get(0).getProfilePictureFileName())
				.isEqualTo(employeeDtos.get(0).getProfilePictureFileName());
		assertThat(employees.get(0).getProfilePictureFilePath())
				.isEqualTo(employeeDtos.get(0).getProfilePictureFilePath());
		//assertThat(employees.get(0).getModeOfWork()).isEqualTo(employeeDtos.get(0).getModeOfWork());
		//assertThat(employees.get(0).getDesignation()).isEqualTo(employeeDtos.get(0).getDesignation());

		assertThat(employees.get(1).getId()).isEqualTo(employeeDtos.get(1).getId());
		assertThat(employees.get(1).getEmailId()).isEqualTo(employeeDtos.get(1).getEmailId());
		assertThat(employees.get(1).getFirstName()).isEqualTo(employeeDtos.get(1).getFirstName());
		assertThat(employees.get(1).getLastName()).isEqualTo(employeeDtos.get(1).getLastName());
		assertThat(employees.get(1).getPhoneNumber()).isEqualTo(employeeDtos.get(1).getPhoneNumber());
		assertThat(employees.get(1).getProfilePictureFileName())
				.isEqualTo(employeeDtos.get(1).getProfilePictureFileName());
		assertThat(employees.get(1).getProfilePictureFilePath())
				.isEqualTo(employeeDtos.get(1).getProfilePictureFilePath());
		//assertThat(employees.get(1).getModeOfWork()).isEqualTo(employeeDtos.get(1).getModeOfWork());
		//assertThat(employees.get(1).getDesignation()).isEqualTo(employeeDtos.get(1).getDesignation());

		when(employeeRepo.findAll()).thenReturn((employees));

		when(modelMapper.map(employees.get(0), EmployeeDto.class)).thenReturn(employeeDtos.get(0));
		when(modelMapper.map(employees.get(1), EmployeeDto.class)).thenReturn(employeeDtos.get(1));

		List<EmployeeDto> allEmployees = this.employeeServiceImpl.getAllEmployees();

		System.out.println(allEmployees);

		assertThat(allEmployees).containsExactly(e1, e2);

	}

	@Test
    void testGetAllEmployee_WithNoEmployees_ReturnsEmptyList() {

        when(employeeRepo.findAll()).thenReturn(Collections.emptyList());

        List<EmployeeDto> employees = employeeServiceImpl.getAllEmployees();
        System.out.println(employees);
        assertThat(employees).isEmpty();

    }

	@Test
	void testGetEmployeeById_WithEmployeeId_Returns_ProfileViewDto() throws Exception {

		designation = Designation.builder().id(1).designationName("DEV").build();
		modeOfWork = ModeOfWork.builder().id(1).modeOfWorkName("Regular").build();

		DesignationDto designationDto = DesignationDto.builder().id(1).name("DEV").build();
		ModeOfWorkDto modeOfWorkDto = ModeOfWorkDto.builder().id(1).name("Regular").build();

//		when(this.modelMapper.map(designation, DesignationDto.class)).thenReturn(designationDto);
//		when(this.modelMapper.map(modeOfWork, ModeOfWorkDto.class)).thenReturn(modeOfWorkDto);

		employee = Employee.builder().id("1").profilePictureFileName("").profilePictureFilePath("").firstName("Harsh")
				.lastName("Patel").emailId("harsh@gmail.com").designation(designation).modeOfWork(modeOfWork)
				.phoneNumber("9924067343").build();

		when(this.employeeRepo.findById("1")).thenReturn(Optional.of(employee));

		city = City.builder().id(1).cityName("Valsad").build();
		floor = Floor.builder().id(1).floorName("1st Floor").city(city).build();
		column = ColumnDetails.builder().id(1).columnName("H").floor(floor).build();
		seat = SeatNumber.builder().id(1).seatNumber(1).column(column).build();

		seatConfiguration = SeatConfiguration.builder().id(1).seatNumber(seat).employee(employee).createdBy(employee)
				.build();
		when(this.seatConfigurationRepo.findByEmployeeAndDeletedByNull(employee)).thenReturn(seatConfiguration);

		CityDto cityDto = CityDto.builder().id(1).name("Valsad").build();
		FloorDto floorDto = FloorDto.builder().id(1).name("1st Floor").build();
		ColumnDetailsDto columnDto = ColumnDetailsDto.builder().id(1).name("H").build();
		SeatNumberDto seatDto = SeatNumberDto.builder().id(1).seatNumber(1).booked(true).build();

//		when(this.modelMapper.map(city, CityDto.class)).thenReturn(cityDto);
//		when(this.modelMapper.map(floor, FloorDto.class)).thenReturn(floorDto);
//		when(this.modelMapper.map(column, ColumnDetailsDto.class)).thenReturn(columnDto);
//		when(this.modelMapper.map(seat, SeatNumberDto.class)).thenReturn(seatDto);

		days.add(WorkingDay.builder().id(1).day("Monday").build());
		days.add(WorkingDay.builder().id(2).day("Tuesday").build());
		days.add(WorkingDay.builder().id(3).day("WednesDay").build());
		days.add(WorkingDay.builder().id(4).day("Thursday").build());
		days.add(WorkingDay.builder().id(5).day("Friday").build());

		Set<WorkingDayDto> dayDto = new HashSet<>();
		dayDto.add(WorkingDayDto.builder().id(1).day("Monday").build());
		dayDto.add(WorkingDayDto.builder().id(2).day("Tuesday").build());
		dayDto.add(WorkingDayDto.builder().id(3).day("WednesDay").build());
		dayDto.add(WorkingDayDto.builder().id(4).day("Thursday").build());
		dayDto.add(WorkingDayDto.builder().id(5).day("Friday").build());

//		when(days.stream().map(day -> this.modelMapper.map(day, WorkingDayDto.class)).collect(Collectors.toSet()))
//				.thenReturn(dayDto);

		profile = ProfileViewDto.builder().profilePictureFileString("").firstName("Harsh").lastName("Patel")
				.emailId("harsh@gmail.com").phoneNumber("9924067349").designation(designationDto)
				.modeOfWork(modeOfWorkDto).city(cityDto).floor(floorDto).column(columnDto).seat(seatDto).days(dayDto)
				.build();

		when(profileMapper.getProfile(employee, seatConfiguration)).thenReturn(profile);

		ProfileViewDto profile1 = this.employeeServiceImpl.getEmployeeById("1");

		assertThat(profile).isEqualTo(profile1);

	}

	@Test
	void testUpdateEmpoyeeById_WithEmployeeId_Returns_ProfileViewDto() throws Exception {

		designation = Designation.builder().id(1).designationName("DEV").build();
		modeOfWork = ModeOfWork.builder().id(1).modeOfWorkName("Regular").build();
		DesignationDto designationDto = DesignationDto.builder().id(1).name("DEV").build();
		ModeOfWorkDto modeOfWorkDto = ModeOfWorkDto.builder().id(1).name("Regular").build();

		employee = Employee.builder().id("1").profilePictureFileName("").profilePictureFilePath("").firstName("Harsh")
				.lastName("Patel").emailId("harsh@gmail.com").designation(designation).modeOfWork(modeOfWork)
				.phoneNumber("9924067343").build();

		Employee updatedEmployee = Employee.builder().id("1").profilePictureFileName("").profilePictureFilePath("")
				.firstName("hrp").lastName("Patel").emailId("harsh@gmail.com").designation(designation)
				.modeOfWork(modeOfWork).phoneNumber("9924067343").build();

		List<EmployeeWorkingDays> employeeWorkingDays = new ArrayList<>();

		city = City.builder().id(1).cityName("Valsad").build();
		floor = Floor.builder().id(1).floorName("1st Floor").city(city).build();
		column = ColumnDetails.builder().id(1).columnName("H").floor(floor).build();
		seat = SeatNumber.builder().id(1).seatNumber(1).column(column).build();

		CityDto cityDto = CityDto.builder().id(1).name("Valsad").build();
		FloorDto floorDto = FloorDto.builder().id(1).name("1st Floor").build();
		ColumnDetailsDto columnDto = ColumnDetailsDto.builder().id(1).name("H").build();
		SeatNumberDto seatDto = SeatNumberDto.builder().id(1).seatNumber(1).booked(true).build();

		seatConfiguration = SeatConfiguration.builder().id(1).seatNumber(seat).employee(employee).createdBy(employee)
				.build();

		days.add(WorkingDay.builder().id(1).day("Monday").build());
		days.add(WorkingDay.builder().id(2).day("Tuesday").build());
		days.add(WorkingDay.builder().id(3).day("WednesDay").build());
		days.add(WorkingDay.builder().id(4).day("Thursday").build());
		days.add(WorkingDay.builder().id(5).day("Friday").build());

		Set<WorkingDayDto> dayDto = new HashSet<>();
		dayDto.add(WorkingDayDto.builder().id(1).day("Monday").build());
		dayDto.add(WorkingDayDto.builder().id(2).day("Tuesday").build());
		dayDto.add(WorkingDayDto.builder().id(3).day("WednesDay").build());
		dayDto.add(WorkingDayDto.builder().id(4).day("Thursday").build());
		dayDto.add(WorkingDayDto.builder().id(5).day("Friday").build());

		UpdateProfileDto newEmployee = UpdateProfileDto.builder().profilePictureFileString("").firstName("hrp")
				.lastName("Patel").designation(1).modeOfWork(1).phoneNumber("9924067343").build();
		newEmployee.setWorkingDays(Set.of(1, 2, 3));

		when(employeeRepo.findById("1")).thenReturn(Optional.of(employee));
		when(updateSeatUtils.getUpdatedEmployee(employee, newEmployee)).thenReturn(updatedEmployee);
		when(updateSeatUtils.saveSeat(updatedEmployee, newEmployee)).thenReturn(seatConfiguration);
		when(seatConfigurationRepo.save(seatConfiguration)).thenReturn(seatConfiguration);
		when(employeeRepo.save(updatedEmployee)).thenReturn(updatedEmployee);
		when(employeeWorkingDaysRepo.findByEmployee(updatedEmployee)).thenReturn(employeeWorkingDays);
		when(workingDaysRepo.findById(anyInt())).thenReturn(Optional.of(new WorkingDay()));

		profile = ProfileViewDto.builder().profilePictureFileString("").firstName("hrp").lastName("Patel")
				.emailId("harsh@gmail.com").phoneNumber("9924067343").designation(designationDto)
				.modeOfWork(modeOfWorkDto).city(cityDto).floor(floorDto).column(columnDto).seat(seatDto).days(dayDto)
				.build();

		when(profileMapper.getProfile(updatedEmployee, seatConfiguration)).thenReturn(profile);

		ProfileViewDto profile1 = this.employeeServiceImpl.updateEmpById("1", newEmployee);
		System.out.println(profile);
		System.out.println(profile1);

		assertThat(profile).isEqualTo(profile1);

	}

}
