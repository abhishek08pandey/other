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

import com.onerivet.deskbook.models.payload.CityDto;
import com.onerivet.deskbook.models.response.GenericResponse;
import com.onerivet.deskbook.services.CityService;

@ExtendWith(MockitoExtension.class)
class CityControllerTest {

	@Mock
    private CityService cityService;

    @InjectMocks
    private CityController cityController;

    @BeforeEach
    void setUp() throws Exception {
        this.cityController = new CityController(this.cityService);
    }

    @Test
    public void testGetCities() {
        
        List<CityDto> cities = Arrays.asList(new CityDto(0, "Valsad"), new CityDto(1, "Surat"));

        Mockito.when(cityService.getAllCities()).thenReturn(cities);

        GenericResponse<List<CityDto>> genericResponse = cityController.getCities();
        System.out.println(genericResponse);

        assertThat(genericResponse.getData().containsAll(cities));

    }

    @Test
    public void testGetCities_WhenNoCitiesFound() {

        CityService mockCityService = Mockito.mock(CityService.class);

        Mockito.when(mockCityService.getAllCities()).thenReturn(Collections.emptyList());

        CityController cityController = new CityController(mockCityService);

        GenericResponse<List<CityDto>> genericResponse = cityController.getCities();

 
        assertThat(genericResponse.getData()).isEmpty();
    }

}
