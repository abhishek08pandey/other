package com.onerivet.deskbook.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.onerivet.deskbook.models.entity.City;
import com.onerivet.deskbook.models.entity.ColumnDetails;
import com.onerivet.deskbook.models.entity.Floor;
import com.onerivet.deskbook.models.entity.SeatNumber;
import com.onerivet.deskbook.models.payload.ColumnDetailsDto;
import com.onerivet.deskbook.models.payload.FloorDto;
import com.onerivet.deskbook.models.payload.SeatNumberDto;
import com.onerivet.deskbook.repository.ColumnDetailsRepo;
import com.onerivet.deskbook.repository.FloorRepo;
import com.onerivet.deskbook.repository.SeatConfigurationRepo;
import com.onerivet.deskbook.repository.SeatNumberRepo;

@ExtendWith(MockitoExtension.class)
class SeatConfigurationServiceImplTest {
	@Mock
	private FloorRepo floorRepo;
	@Mock
	private ColumnDetailsRepo columnDetailsRepo;
	@Mock
	private SeatNumberRepo seatNumberRepo;
	@Mock
	private SeatConfigurationRepo seatConfigurationRepo;

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	private SeatConfigurationServiceImpl seatConfigurationService;

	@BeforeEach
	public void setUp() {

		seatConfigurationService = new SeatConfigurationServiceImpl(floorRepo, columnDetailsRepo, seatNumberRepo,
				seatConfigurationRepo, modelMapper);
	}

	@Test
	public void testGetAllFloors_WithCityId_ReturnsFloorDtoList() {

		int cityId = 1;
		List<Floor> floors = Arrays.asList(new Floor(1), new Floor(2));
		List<FloorDto> expectedFloorDto = Arrays.asList(new FloorDto(1), new FloorDto(2));
		when(this.floorRepo.findByCity(any(City.class))).thenReturn(floors);
		when(modelMapper.map(floors.get(0), FloorDto.class)).thenReturn(expectedFloorDto.get(0));
		when(modelMapper.map(floors.get(1), FloorDto.class)).thenReturn(expectedFloorDto.get(1));
		List<FloorDto> actualFloorDto = this.seatConfigurationService.getAllFloors(cityId);
		assertThat(expectedFloorDto.size()).isGreaterThan(0);
		assertThat(actualFloorDto).containsAll(expectedFloorDto);
	}

	@Test
	public void testGetAllColumns_WithFloorId_ReturnsColumnDetailsDtoList() {
		int floorId = 1;
		List<ColumnDetails> column = Arrays.asList(new ColumnDetails(1), new ColumnDetails(2));
		List<ColumnDetailsDto> expectedColumnDto = Arrays.asList(new ColumnDetailsDto(1), new ColumnDetailsDto(2));
		when(this.columnDetailsRepo.findByFloor(any(Floor.class))).thenReturn(column);
		when(modelMapper.map(column.get(0), ColumnDetailsDto.class)).thenReturn(expectedColumnDto.get(0));
		when(modelMapper.map(column.get(1), ColumnDetailsDto.class)).thenReturn(expectedColumnDto.get(1));
		List<ColumnDetailsDto> actualColumnDto = this.seatConfigurationService.getAllColumns(floorId);
		assertThat(expectedColumnDto.size()).isGreaterThan(0);
		assertThat(actualColumnDto).containsAll(expectedColumnDto);
	}

	@Test
	public void testGetAllSeats_WithColumnId_ReturnsSeatNumberDtoList() {
		int columnId = 1;
		List<SeatNumber> seats = Arrays.asList(new SeatNumber(1), new SeatNumber(2));
		List<SeatNumberDto> expectedSeatDto = Arrays.asList(new SeatNumberDto(1), new SeatNumberDto(2));
		when(this.seatNumberRepo.findByColumn(any(ColumnDetails.class))).thenReturn(seats);
		when(modelMapper.map(seats.get(0), SeatNumberDto.class)).thenReturn(expectedSeatDto.get(0));
		when(modelMapper.map(seats.get(1), SeatNumberDto.class)).thenReturn(expectedSeatDto.get(1));
		List<SeatNumberDto> actualSeatDto = this.seatConfigurationService.getAllSeats(columnId);
		assertThat(expectedSeatDto.size()).isGreaterThan(0);
		assertThat(actualSeatDto).containsAll(expectedSeatDto);
	}
}
