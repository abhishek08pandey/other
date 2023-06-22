package com.onerivet.deskbook.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

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
import org.modelmapper.ModelMapper;

import com.onerivet.deskbook.models.entity.ModeOfWork;
import com.onerivet.deskbook.models.payload.ModeOfWorkDto;
import com.onerivet.deskbook.repository.ModeOfWorkRepo;

@ExtendWith(MockitoExtension.class)
class ModeOfWorkServiceImplTest {

	@Mock
    private ModeOfWorkRepo modeOfWorkRepo;

    @InjectMocks
    private ModeOfWorkServiceImpl modeOfWorkServiceImpl;

    private List<ModeOfWork> modeOfWork;

    private List<ModeOfWorkDto> modeOfWorkDto;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() throws Exception {
        this.modeOfWorkServiceImpl = new ModeOfWorkServiceImpl(modeOfWorkRepo, modelMapper);
    }

    @Test
    void testGetAllModeOfWork_WithModeOfWork_ReturnsModeOfWorkDtoList() {

        Mockito.mock(ModeOfWork.class);
        Mockito.mock(ModeOfWorkDto.class);
        Mockito.mock(ModeOfWorkServiceImpl.class);

        modeOfWork = Arrays.asList(new ModeOfWork(1, "Hybrid"), new ModeOfWork(2, "Work From Home"),
                new ModeOfWork(3, "Regular"));
        modeOfWorkDto = Arrays.asList(new ModeOfWorkDto(1, "Hybrid"), new ModeOfWorkDto(2, "Work From Home"),
                new ModeOfWorkDto(3, "Regular"));

        assertThat(modeOfWork.get(0).getId()).isEqualTo(modeOfWorkDto.get(0).getId());
        assertThat(modeOfWork.get(0).getModeOfWorkName()).isEqualTo(modeOfWorkDto.get(0).getName());

        assertThat(modeOfWork.get(1).getId()).isEqualTo(modeOfWorkDto.get(1).getId());
        assertThat(modeOfWork.get(1).getModeOfWorkName()).isEqualTo(modeOfWorkDto.get(1).getName());

        assertThat(modeOfWork.get(2).getId()).isEqualTo(modeOfWorkDto.get(2).getId());
        assertThat(modeOfWork.get(2).getModeOfWorkName()).isEqualTo(modeOfWorkDto.get(2).getName());

        System.out.println(modeOfWork);
        System.out.println(modeOfWorkDto);

        when(this.modeOfWorkRepo.findAll()).thenReturn(modeOfWork);

        when(modelMapper.map(modeOfWork.get(0), ModeOfWorkDto.class)).thenReturn(modeOfWorkDto.get(0));
        when(modelMapper.map(modeOfWork.get(1), ModeOfWorkDto.class)).thenReturn(modeOfWorkDto.get(1));
        when(modelMapper.map(modeOfWork.get(2), ModeOfWorkDto.class)).thenReturn(modeOfWorkDto.get(2));

        List<ModeOfWorkDto> modeOfWorkDto1 = this.modeOfWorkServiceImpl.getAllModeOfWorks();

        System.out.println(modeOfWorkDto1);
        System.out.println(modeOfWorkDto);

        assertThat(modeOfWorkDto1).isEqualTo(modeOfWorkDto);

    }

    @Test
    void testModeOfWork_Return_Empty() {

        when(this.modeOfWorkRepo.findAll()).thenReturn(Collections.emptyList());

        List<ModeOfWorkDto> modeOfWorkDto = modeOfWorkServiceImpl.getAllModeOfWorks();

        assertThat(modeOfWorkDto).isEmpty();
    }

}
