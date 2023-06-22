package com.onerivet.deskbook.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.onerivet.deskbook.models.entity.City;
import com.onerivet.deskbook.models.entity.ColumnDetails;
import com.onerivet.deskbook.models.entity.Floor;
import com.onerivet.deskbook.models.entity.SeatNumber;
import com.onerivet.deskbook.models.entity.SeatView;
import com.onerivet.deskbook.models.entity.WorkingDay;

@ExtendWith(MockitoExtension.class)
class SeatNumberRepoTest {
	@Mock
	private SeatNumberRepo seatNumberRepo;
	private City city;
	private Floor floor;
	private SeatNumber seatNumber;
	private ColumnDetails columnDetails;

	@BeforeEach
	void setUp() throws Exception {
		city = new City();
		city.setId(2);
		city.setCityName("Valsad");
		floor = new Floor();
		floor.setId(2);
		floor.setFloorName("1st floor");
		floor.setCity(city);
		columnDetails = new ColumnDetails();
		columnDetails.setId(2);
		columnDetails.setColumnName("A");
		columnDetails.setFloor(floor);
		seatNumber = new SeatNumber();
		seatNumber.setId(2);
		seatNumber.setColumn(columnDetails);
		seatNumber.setBooked(true);
		seatNumber.setSeatNumber(1);
	}

	@Test
	void testFindByColumn_WithColumnDetails_ReturnsSeatList() {
		List<SeatNumber> seatNumberList = new ArrayList<>();
		seatNumberList.add(seatNumber);
		when(seatNumberRepo.findByColumn(columnDetails)).thenReturn(seatNumberList);
		List<SeatNumber> findByColumn = seatNumberRepo.findByColumn(columnDetails);
		assertNotNull(findByColumn);
		assertEquals(1, findByColumn.size());
		System.out.println(findByColumn);
	}

	@Test
	void testFindColumnFloorCityBySeat_WithSeat_ReturnsColumnDetailsMap() {
		Map<String, ColumnDetails> seatMap = new HashMap<>();
		seatMap.put("A", columnDetails);
		when(seatNumberRepo.findColumnFloorCityBySeat(1)).thenReturn(seatMap);
		Map<String, ColumnDetails> findColumnFloorCityBySeat = seatNumberRepo.findColumnFloorCityBySeat(1);
		assertNotNull(findColumnFloorCityBySeat);
		assertEquals(1, findColumnFloorCityBySeat.size());
		ColumnDetails details = findColumnFloorCityBySeat.get("A");
		System.out.println(details);
		assertNotNull(details);
		assertEquals("A", details.getColumnName());
	}

	@Test
	public void testGetSeat_WhenNoSeatFound() {
		SeatNumber seatNumber = new SeatNumber();
		seatNumber.setId(1);
		when(seatNumberRepo.findByColumn(columnDetails)).thenReturn(Collections.emptyList());
		List<SeatNumber> foundseats = seatNumberRepo.findByColumn(columnDetails);
		System.out.println(foundseats);
		Assertions.assertThat(foundseats.isEmpty());
	}

	@Test
	public void testColumnFloorCityBySeat_WhenNoColumnFloorCityBySeat() {
		ColumnDetails columnDetails = new ColumnDetails();
		columnDetails.setId(1);
		when(seatNumberRepo.findColumnFloorCityBySeat(1)).thenReturn(Collections.emptyMap());
		Map<String, ColumnDetails> foundColumnFloorCityBySeat = seatNumberRepo.findColumnFloorCityBySeat(1);
		System.out.println(foundColumnFloorCityBySeat);
		Assertions.assertThat(foundColumnFloorCityBySeat.isEmpty());
	}
	@Test
    public void testGetViewByDateAndCityAndFloorAndWorkingDay() {
        Floor floor = new Floor();
        City city = new City();
        WorkingDay workingDay = new WorkingDay();
        
        List<SeatView> seatViews = Arrays.asList(new SeatView(new SeatNumber(1), new ColumnDetails(1), "Reserved"),
				new SeatView(new SeatNumber(2), new ColumnDetails(2), "Booked"));


        when(seatNumberRepo.getViewByDateAndCityAndFloorAndWorkingDay(
                any(LocalDate.class), any(City.class), any(Floor.class), any(WorkingDay.class)
        )).thenReturn(seatViews);

        List<SeatView> seatViewList = seatNumberRepo.getViewByDateAndCityAndFloorAndWorkingDay(
                LocalDate.now(), city, floor, workingDay
        );

        assertEquals(2, seatViewList.size());
        assertThat(seatViewList).containsExactly(seatViews.get(0), seatViews.get(1));
    }
}