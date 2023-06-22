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

import com.onerivet.deskbook.models.payload.DesignationDto;
import com.onerivet.deskbook.models.response.GenericResponse;
import com.onerivet.deskbook.services.DesignationService;
import com.onerivet.deskbook.services.impl.DesignationServiceImpl;

@ExtendWith(MockitoExtension.class)
class DesignationControllerTest {

	@Mock
	private DesignationServiceImpl designationServiceImpl;

	@Mock
	private DesignationService designationService;

	@InjectMocks
	private DesignationController designationController;

	@Mock
	private GenericResponse<List<DesignationDto>> listOfDesignation;

	private List<DesignationDto> designationDto;

	@BeforeEach
	void setUp() {
		this.designationController = new DesignationController(this.designationService);
	}

	@Test
	void testGetDesignations() {

		Mockito.mock(DesignationDto.class);
		Mockito.mock(DesignationController.class);

		designationDto = Arrays.asList(new DesignationDto(1, "BA"), new DesignationDto(2, "PM"),
				new DesignationDto(3, "INFRA"), new DesignationDto(4, "DEVOPS"), new DesignationDto(5, "DEVELOPER"),
				new DesignationDto(6, "ADMIN"), new DesignationDto(7, "QA"), new DesignationDto(8, "UI/UX"),
				new DesignationDto(9, "HR"), new DesignationDto(10, "ACCOUNTS"));

		listOfDesignation = new GenericResponse<List<DesignationDto>>(designationDto, null);

//        assertThat(designationDto.get(0).getId()).isEqualTo(listOfDesignation.getData().get(0).getId());
//        assertThat(designationDto.get(0).getName()).isEqualTo(listOfDesignation.getData().get(0).getName());

		Mockito.when(designationService.getAllDesignations()).thenReturn(designationDto);

		GenericResponse<List<DesignationDto>> designations = designationController.getDesignations();

		System.out.println(designations);

		assertThat(designations.getData()).isEqualTo(listOfDesignation.getData());
//        assertThat(designations.getError()).isEqualTo(listOfDesignation.getError());
//        assertThat(designations).isEqualTo(listOfDesignation);
	}

	@Test
	void testGetDesignations_Return_Empty() {

		Mockito.mock(DesignationService.class);

		Mockito.when(designationService.getAllDesignations()).thenReturn(Collections.emptyList());

		GenericResponse<List<DesignationDto>> designations = designationController.getDesignations();

		assertThat(designations.getData()).isEmpty();
	}
}
