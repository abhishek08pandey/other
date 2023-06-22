package com.onerivet.deskbook.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.onerivet.deskbook.models.payload.WorkingDayDto;
import com.onerivet.deskbook.models.response.GenericResponse;
import com.onerivet.deskbook.services.WorkingDayService;

@ExtendWith(MockitoExtension.class)
class WorkingDayControllerTest {

	  	@Mock
	    private WorkingDayService workingDayService;

	    @InjectMocks
	    private WorkingDayController workingDayController;

	    

	    @BeforeEach
	    void setUp() throws Exception {
	        this.workingDayController = new WorkingDayController(workingDayService);
	    }

	    @Test
	    void testWorkingDayController_WithAllWorkingDays_ReturnListOfWorkingDays() {
	        List<WorkingDayDto> workingdays = Arrays.asList(new WorkingDayDto(0, "Monday"), new WorkingDayDto(1, "Tuesday"),
	                new WorkingDayDto(2, "Wednesday"), new WorkingDayDto(3, "Thursday"), new WorkingDayDto(4, "Friday"));

	        Mockito.when(workingDayService.getAllWorkingDays()).thenReturn(workingdays);
	        GenericResponse<List<WorkingDayDto>> genericResponse = workingDayController.getWorkingDays();
	        System.out.println(genericResponse);
	        assertThat(genericResponse.getData().containsAll(workingdays));
	    }

	    @Test
	    void testGetWorkingDays_WhenNoWorkingDaysFound() {
	        WorkingDayService mockWorkingdayService = Mockito.mock(WorkingDayService.class);
	        Mockito.when(mockWorkingdayService.getAllWorkingDays()).thenReturn(Collections.emptyList());
	        WorkingDayController workingDayController = new WorkingDayController(mockWorkingdayService);
	        GenericResponse<List<WorkingDayDto>> genericResponse = workingDayController.getWorkingDays();

	        assertThat(genericResponse.getData()).isEmpty();
	    }


}
