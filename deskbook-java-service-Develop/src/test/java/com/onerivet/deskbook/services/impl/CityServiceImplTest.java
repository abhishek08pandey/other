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

import com.onerivet.deskbook.models.entity.City;
import com.onerivet.deskbook.models.payload.CityDto;
import com.onerivet.deskbook.repository.CityRepo;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

	@Mock
	private CityRepo cityRepo;

	@Mock
	private ModelMapper modelMapper;

	private CityServiceImpl cityServiceImpl;

	private List<City> city;
	private List<CityDto> cityDto;

	@BeforeEach
	void setUp() throws Exception {
		this.cityServiceImpl = new CityServiceImpl(cityRepo, modelMapper);
	}

	@Test
	void testGetAllCities_WithAllCities_ReturnsCityDtoList() {
		city = Arrays.asList(new City(0, "Valsad"), new City(1, "Surat"));
		cityDto = Arrays.asList(new CityDto(0, "Valsad"), new CityDto(1, "Surat"));

		assertThat(city.get(0).getId()).isEqualTo(cityDto.get(0).getId());
		assertThat(city.get(0).getCityName()).isEqualTo(cityDto.get(0).getName());

		assertThat(city.get(1).getId()).isEqualTo(cityDto.get(1).getId());
		assertThat(city.get(1).getCityName()).isEqualTo(cityDto.get(1).getName());

		when(cityRepo.findAll()).thenReturn(city);

		when(modelMapper.map(city.get(0), CityDto.class)).thenReturn(cityDto.get(0));
		when(modelMapper.map(city.get(1), CityDto.class)).thenReturn(cityDto.get(1));

		List<CityDto> cities = cityServiceImpl.getAllCities();
		System.out.println(cities);
		assertThat(cities).containsExactly(cityDto.get(0), cityDto.get(1));
	}

	@Test
	void testGetAllCities_WithNoCities_ReturnsEmptyList() {

	    when(cityRepo.findAll()).thenReturn(Collections.emptyList());

	    List<CityDto> cities = cityServiceImpl.getAllCities();
	    System.out.println(cities);
	    assertThat(cities).isEmpty();

	}

}
