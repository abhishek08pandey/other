package com.onerivet.deskbook.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.onerivet.deskbook.models.payload.CityDto;
import com.onerivet.deskbook.models.payload.ColumnDetailsDto;
import com.onerivet.deskbook.models.payload.DesignationDto;
import com.onerivet.deskbook.models.payload.FloorDto;
import com.onerivet.deskbook.models.payload.ModeOfWorkDto;
import com.onerivet.deskbook.models.payload.ProfileViewDto;
import com.onerivet.deskbook.models.payload.SeatNumberDto;
import com.onerivet.deskbook.models.payload.UpdateProfileDto;
import com.onerivet.deskbook.models.payload.WorkingDayDto;
import com.onerivet.deskbook.models.response.GenericResponse;
import com.onerivet.deskbook.services.EmployeeService;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private UpdateProfileDto updateProfileDto;

    private Principal principal;

    private ProfileViewDto profileViewDto;

    private DesignationDto designation;

    private ModeOfWorkDto modeOfWork;
    
    private CityDto city;

    private FloorDto floor;

    private ColumnDetailsDto column;

    private SeatNumberDto seat;

    private Set<WorkingDayDto> days;

    @Mock
    private GenericResponse<ProfileViewDto> genericResponse;

    @BeforeEach
    void setUp() {
        this.employeeController = new EmployeeController(employeeService);
        
        designation = new DesignationDto(1, "BA");
        modeOfWork = new ModeOfWorkDto(1, "Hybrid");
        city = new CityDto(1, "Surat");
        floor = new FloorDto(2, "1st Floor");
        column = new ColumnDetailsDto(2, "A");
        seat = new SeatNumberDto(2, 1, false, true);
        days = new HashSet<>(1,2);
    }
    
    @Test
    public void testGetById_ReturnsProfileViewDto() throws Exception {

    	principal = ()-> "gdgsg-uyr73h-daha-y33h";
    	profileViewDto = new ProfileViewDto("ProfilepicBase64String", "abhishek@gmail.com", "Abhishek", "Pandey", "9685743215",
                designation, modeOfWork, city, floor, column, seat, days, true);
        when(employeeService.getEmployeeById(principal.getName())).thenReturn(profileViewDto);

        GenericResponse<ProfileViewDto> response = employeeController.getById(principal);
        System.out.println(response);

        verify(employeeService).getEmployeeById(principal.getName());

        assertThat(response.getData().equals(profileViewDto));
    }

    @Test
    void testUpdateById_WithEmployeeId_ReturnsProfileViewDto() throws Exception {
        
        Mockito.mock(EmployeeController.class);
        Mockito.mock(UpdateProfileDto.class);
        
        principal = ()-> "gdgsg-uyr73h-daha-y33h";
           
        updateProfileDto = new UpdateProfileDto("ProfilepicBase64String", "Abhishek", "Pandey", "9685743215",
                1, 1, 1, 2, 2, 2, new HashSet<Integer>(1,2));
        
        profileViewDto = new ProfileViewDto("ProfilepicBase64String", "abhishek@gmail.com", "Abhishek", "Pandey", "9685743215",
                designation, modeOfWork, city, floor, column, seat, days, true);
    
        genericResponse = new GenericResponse<ProfileViewDto>(profileViewDto, null);
        
        Mockito.when(employeeService.updateEmpById(principal.getName(), updateProfileDto)).thenReturn(profileViewDto);
            
        ResponseEntity<GenericResponse<ProfileViewDto>> updateById = employeeController.updateById(principal, updateProfileDto);
        
        System.out.println(profileViewDto);
        
        assertThat(updateById.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(updateById.getBody().getData()).isEqualTo(profileViewDto);
        assertNull(updateById.getBody().getError());
        
    }

}
