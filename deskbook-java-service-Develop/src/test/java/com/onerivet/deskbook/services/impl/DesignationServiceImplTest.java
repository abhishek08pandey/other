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

import com.onerivet.deskbook.models.entity.Designation;
import com.onerivet.deskbook.models.payload.DesignationDto;
import com.onerivet.deskbook.repository.DesignationRepo;

@ExtendWith(MockitoExtension.class)
class DesignationServiceImplTest {

	@Mock
	private DesignationRepo designationRepo;

	@InjectMocks
	private DesignationServiceImpl designationServiceImpl;

	private List<Designation> designation;

	private List<DesignationDto> designationDto;

	@Mock
	private ModelMapper modelMapper;

	@BeforeEach
	void setUp() {
		this.designationServiceImpl = new DesignationServiceImpl(this.designationRepo, modelMapper);
	}
	
	@Test
	void testAllDesignation_WithDesignations_ReturnsDesignationDtoList() {

		Mockito.mock(Designation.class);
		Mockito.mock(DesignationDto.class);
		Mockito.mock(DesignationServiceImpl.class);

		designation = Arrays.asList(new Designation(1, "BA"), new Designation(2, "PM"), new Designation(3, "INFRA"),
				new Designation(4, "DEVOPS"), new Designation(5, "DEVELOPER"), new Designation(6, "ADMIN"),
				new Designation(7, "QA"), new Designation(8, "UI/UX"), new Designation(9, "HR"),
				new Designation(10, "ACCOUNTS"));

		designationDto = Arrays.asList(new DesignationDto(1, "BA"), new DesignationDto(2, "PM"),
				new DesignationDto(3, "INFRA"), new DesignationDto(4, "DEVOPS"), new DesignationDto(5, "DEVELOPER"),
				new DesignationDto(6, "ADMIN"), new DesignationDto(7, "QA"), new DesignationDto(8, "UI/UX"),
				new DesignationDto(9, "HR"), new DesignationDto(10, "ACCOUNTS"));

		assertThat(designation.get(0).getId()).isEqualTo(designationDto.get(0).getId());
		assertThat(designation.get(0).getDesignationName()).isEqualTo(designationDto.get(0).getName());

		assertThat(designation.get(1).getId()).isEqualTo(designationDto.get(1).getId());
		assertThat(designation.get(1).getDesignationName()).isEqualTo(designationDto.get(1).getName());

		assertThat(designation.get(2).getId()).isEqualTo(designationDto.get(2).getId());
		assertThat(designation.get(2).getDesignationName()).isEqualTo(designationDto.get(2).getName());

		assertThat(designation.get(3).getId()).isEqualTo(designationDto.get(3).getId());
		assertThat(designation.get(3).getDesignationName()).isEqualTo(designationDto.get(3).getName());

		assertThat(designation.get(4).getId()).isEqualTo(designationDto.get(4).getId());
		assertThat(designation.get(4).getDesignationName()).isEqualTo(designationDto.get(4).getName());

		assertThat(designation.get(5).getId()).isEqualTo(designationDto.get(5).getId());
		assertThat(designation.get(5).getDesignationName()).isEqualTo(designationDto.get(5).getName());

		assertThat(designation.get(6).getId()).isEqualTo(designationDto.get(6).getId());
		assertThat(designation.get(6).getDesignationName()).isEqualTo(designationDto.get(6).getName());

		assertThat(designation.get(7).getId()).isEqualTo(designationDto.get(7).getId());
		assertThat(designation.get(7).getDesignationName()).isEqualTo(designationDto.get(7).getName());

		assertThat(designation.get(8).getId()).isEqualTo(designationDto.get(8).getId());
		assertThat(designation.get(8).getDesignationName()).isEqualTo(designationDto.get(8).getName());

		assertThat(designation.get(9).getId()).isEqualTo(designationDto.get(9).getId());
		assertThat(designation.get(9).getDesignationName()).isEqualTo(designationDto.get(9).getName());

		when(this.designationRepo.findAll()).thenReturn(designation);

		when(modelMapper.map(designation.get(0), DesignationDto.class)).thenReturn(designationDto.get(0));
		when(modelMapper.map(designation.get(1), DesignationDto.class)).thenReturn(designationDto.get(1));
		when(modelMapper.map(designation.get(2), DesignationDto.class)).thenReturn(designationDto.get(2));
		when(modelMapper.map(designation.get(3), DesignationDto.class)).thenReturn(designationDto.get(3));
		when(modelMapper.map(designation.get(4), DesignationDto.class)).thenReturn(designationDto.get(4));
		when(modelMapper.map(designation.get(5), DesignationDto.class)).thenReturn(designationDto.get(5));
		when(modelMapper.map(designation.get(6), DesignationDto.class)).thenReturn(designationDto.get(6));
		when(modelMapper.map(designation.get(7), DesignationDto.class)).thenReturn(designationDto.get(7));
		when(modelMapper.map(designation.get(8), DesignationDto.class)).thenReturn(designationDto.get(8));
		when(modelMapper.map(designation.get(9), DesignationDto.class)).thenReturn(designationDto.get(9));

		List<DesignationDto> designationDto1 = this.designationServiceImpl.getAllDesignations();

		System.out.println(designationDto1);
		
		assertThat(designationDto1).isEqualTo(designationDto);
	}
	@Test
	void testDesignation_WithNoDesignations_ReturnsEmptyList() {
	        
	     when(this.designationRepo.findAll()).thenReturn(Collections.emptyList());
	        
	     List<DesignationDto> designationDto = designationServiceImpl.getAllDesignations();
	        
	     assertThat(designationDto).isEmpty();
	 }
}
