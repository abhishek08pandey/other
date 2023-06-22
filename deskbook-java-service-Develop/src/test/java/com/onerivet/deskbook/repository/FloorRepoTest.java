package com.onerivet.deskbook.repository;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.onerivet.deskbook.models.entity.City;
import com.onerivet.deskbook.models.entity.Floor;

@ExtendWith(MockitoExtension.class)
class FloorRepoTest {
    @Mock
    private FloorRepo floorRepo;

    @Mock
    private City city;

    @BeforeEach
    void setUp() {
        Mockito.mock(FloorRepo.class);
        Mockito.mock(City.class);
    }

    @Test
    void testFindByCity_WithFindFloor_ReturnsListOfFloor() {

        city.setId(1);

        Floor floor1 = new Floor();
        floor1.setId(1);
        floor1.setCity(city);

        Floor floor2 = new Floor();
        floor2.setId(2);
        floor2.setCity(city);

        List<Floor> floorsList = Arrays.asList(floor1, floor2);
        floorRepo.saveAll(floorsList);

        when(floorRepo.findByCity(city)).thenReturn(floorsList);

        List<Floor> foundFloors = floorRepo.findByCity(city);

        Assertions.assertThat(foundFloors.size()).isEqualTo(2);
        Assertions.assertThat(foundFloors).contains(floor1, floor2);

    }

    @Test
    void testFindByEmployee_WithFindWorkingDays_ReturnsEmptyList() {

        City city = new City();
        city.setId(1);

        when(floorRepo.findByCity(city)).thenReturn(Collections.emptyList());

        List<Floor> foundFloors = floorRepo.findByCity(city);

        Assertions.assertThat(foundFloors.isEmpty());

    }
}