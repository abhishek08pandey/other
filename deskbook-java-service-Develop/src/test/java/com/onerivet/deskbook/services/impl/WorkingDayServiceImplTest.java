package com.onerivet.deskbook.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.onerivet.deskbook.models.entity.WorkingDay;
import com.onerivet.deskbook.models.payload.WorkingDayDto;
import com.onerivet.deskbook.repository.WorkingDaysRepo;

@ExtendWith(MockitoExtension.class)
class WorkingDayServiceImplTest {

	@Mock
    private WorkingDaysRepo workingDaysRepo;

    @Mock
    private ModelMapper modelMapper;

    private WorkingDayServiceImpl workingDayServiceImpl;

    private List<WorkingDay> workingDays;
    private List<WorkingDayDto> workingDaysDto;

    @BeforeEach
    void setUp() throws Exception {
        this.workingDayServiceImpl=new WorkingDayServiceImpl(workingDaysRepo, modelMapper);
    }

    @Test
    void testGetAllWorkingDays_WithAllWorkingDays_ReturnsWorkingDaysDtoList() {
        
        workingDays=Arrays.asList(new WorkingDay(0, "Monday"),new WorkingDay(1, "Tuesday"),new WorkingDay(2,"Wednesday"),new WorkingDay(3,"Thursday"),new WorkingDay(4,"Friday"));
        workingDaysDto=Arrays.asList(new WorkingDayDto(0, "Monday"),new WorkingDayDto(1, "Tuesday"),new WorkingDayDto(2,"Wednesday"),new WorkingDayDto(3,"Thursday"),new WorkingDayDto(4,"Friday"));
        
        when(workingDaysRepo.findAll()).thenReturn(workingDays);

   
        assertThat(workingDays.get(0).getId()).isEqualTo(workingDaysDto.get(0).getId());
        assertThat(workingDays.get(0).getDay()).isEqualTo(workingDaysDto.get(0).getDay());
        
        assertThat(workingDays.get(1).getId()).isEqualTo(workingDaysDto.get(1).getId());
        assertThat(workingDays.get(1).getDay()).isEqualTo(workingDaysDto.get(1).getDay());
        
        assertThat(workingDays.get(2).getId()).isEqualTo(workingDaysDto.get(2).getId());
        assertThat(workingDays.get(2).getDay()).isEqualTo(workingDaysDto.get(2).getDay());
        
        assertThat(workingDays.get(3).getId()).isEqualTo(workingDaysDto.get(3).getId());
        assertThat(workingDays.get(3).getDay()).isEqualTo(workingDaysDto.get(3).getDay());
        
        assertThat(workingDays.get(4).getId()).isEqualTo(workingDaysDto.get(4).getId());
        assertThat(workingDays.get(4).getDay()).isEqualTo(workingDaysDto.get(4).getDay());
        
        when(modelMapper.map(workingDays.get(0), WorkingDayDto.class)).thenReturn(workingDaysDto.get(0));
        when(modelMapper.map(workingDays.get(1), WorkingDayDto.class)).thenReturn(workingDaysDto.get(1));
        when(modelMapper.map(workingDays.get(2), WorkingDayDto.class)).thenReturn(workingDaysDto.get(2));
        when(modelMapper.map(workingDays.get(3), WorkingDayDto.class)).thenReturn(workingDaysDto.get(3));
        when(modelMapper.map(workingDays.get(4), WorkingDayDto.class)).thenReturn(workingDaysDto.get(4));
        
        List<WorkingDayDto> days=workingDayServiceImpl.getAllWorkingDays();
        
        System.out.println(days);
        assertThat(days).isEqualTo(workingDaysDto);
    }
    @Test
    void testGetAllWorkingDays_WithNoWorkingDays_ReturnsEmptyList() {
    	
    	when(workingDaysRepo.findAll()).thenReturn(Collections.emptyList());
        List<WorkingDayDto> days=workingDayServiceImpl.getAllWorkingDays();
        
        System.out.println(days);
        assertThat(days).isEmpty();
    }
    
}
