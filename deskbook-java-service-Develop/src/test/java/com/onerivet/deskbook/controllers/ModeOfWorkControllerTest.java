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

import com.onerivet.deskbook.models.payload.ModeOfWorkDto;
import com.onerivet.deskbook.models.response.GenericResponse;
import com.onerivet.deskbook.services.ModeOfWorkService;

@ExtendWith(MockitoExtension.class)
class ModeOfWorkControllerTest {

    @Mock private ModeOfWorkService modeOfWorkService;
    
    @InjectMocks private ModeOfWorkController modeOfWorkController;
    
    private List<ModeOfWorkDto> modeOfWorkDto;
    
    private GenericResponse<List<ModeOfWorkDto>> listOfModeOfWork;
    
    @BeforeEach
    void setUp() {
        this.modeOfWorkController = new ModeOfWorkController(this.modeOfWorkService);
    }
    
    @Test
    void testGetModeOfWorks() {
        
        Mockito.mock(ModeOfWorkDto.class);
        Mockito.mock(ModeOfWorkController.class);
        
        modeOfWorkDto = Arrays.asList(new ModeOfWorkDto(1, "Hybrid"), new ModeOfWorkDto(2, "Work From Home"),
                new ModeOfWorkDto(3, "Regular"));
        
        listOfModeOfWork = new GenericResponse<List<ModeOfWorkDto>>(modeOfWorkDto, null);
        
        Mockito.when(modeOfWorkService.getAllModeOfWorks()).thenReturn(modeOfWorkDto);
        
        GenericResponse<List<ModeOfWorkDto>> modeOfWorks = modeOfWorkController.getModeOfWorks();
        
        System.out.println(modeOfWorks);
        
        assertThat(modeOfWorks.getData()).isEqualTo(listOfModeOfWork.getData());
    }
    
    @Test
    void testGetModeOfWorks_Return_Empty() {
        
        Mockito.mock(ModeOfWorkService.class);
        
        Mockito.when(modeOfWorkService.getAllModeOfWorks()).thenReturn(Collections.emptyList());
        
        GenericResponse<List<ModeOfWorkDto>> modeOfWorks = modeOfWorkController.getModeOfWorks();
        
        assertThat(modeOfWorks.getData()).isEmpty();
    }
}