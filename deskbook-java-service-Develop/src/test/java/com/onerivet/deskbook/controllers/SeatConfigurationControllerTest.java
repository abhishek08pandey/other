package com.onerivet.deskbook.controllers;

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

import com.onerivet.deskbook.models.entity.SeatConfiguration;
import com.onerivet.deskbook.models.payload.ColumnDetailsDto;
import com.onerivet.deskbook.models.payload.FloorDto;
import com.onerivet.deskbook.models.payload.SeatNumberDto;
import com.onerivet.deskbook.models.response.GenericResponse;
import com.onerivet.deskbook.services.SeatConfigurationService;

@ExtendWith(MockitoExtension.class)
class SeatConfigurationControllerTest {

    @Mock private SeatConfigurationService seatConfigurationService;
    
    @InjectMocks private SeatConfigurationController seatConfigurationController;
    
    private List<FloorDto> floorDto;
    private GenericResponse<List<FloorDto>> listOfFloors;
    
    private List<ColumnDetailsDto> columnDetailsDto;
    private GenericResponse<List<ColumnDetailsDto>> listOfColumns;
    
    private List<SeatNumberDto> seatNumberDto;
    private GenericResponse<List<SeatNumberDto>> listOfSeat;
    
    @BeforeEach
    void setUp() {
        this.seatConfigurationController = new SeatConfigurationController(seatConfigurationService);
    }
    
    @Test
    void testGetFloors_WithCityId_Returns_FloorDtoLists() {
        
        Mockito.mock(SeatConfiguration.class);
        Mockito.mock(SeatConfigurationController.class);
        
        //CityDto city = new CityDto(1);// 1 surat, 2 valsad
        
        floorDto = Arrays.asList(new FloorDto(2, "1st Floor"), new FloorDto(3, "2nd Floor"));
        
        listOfFloors = new GenericResponse<List<FloorDto>>(floorDto, null);
        
        Mockito.when(seatConfigurationService.getAllFloors(1)).thenReturn(floorDto);// city ID
        
        GenericResponse<List<FloorDto>> floors = seatConfigurationController.getFloors(1);
        
        System.out.println(floors);
        assertThat(floors.getData()).isEqualTo(listOfFloors.getData());
        
    }
    
    @Test
    void testGetFloors_Return_Empty() {
        
        Mockito.mock(SeatConfigurationService.class);
        
        Mockito.when(seatConfigurationService.getAllFloors(1)).thenReturn(Collections.emptyList());
        
        GenericResponse<List<FloorDto>> floors = seatConfigurationController.getFloors(1);
        
        assertThat(floors.getData()).isEmpty();
    }
    
    @Test
    void testGetColumns_WithFloorId_Returns_ColumnDetailsDtoList() {
        
        Mockito.mock(ColumnDetailsDto.class);
        Mockito.mock(SeatConfigurationController.class);
        
        //FloorDto floorDto = new FloorDto(2); // 1st Floor
        
        columnDetailsDto = Arrays.asList(new ColumnDetailsDto(1, "A"), new ColumnDetailsDto(2, "B"));
        
        listOfColumns = new GenericResponse<List<ColumnDetailsDto>>(columnDetailsDto, null);
        
        when(seatConfigurationService.getAllColumns(1)).thenReturn(columnDetailsDto);
        
        GenericResponse<List<ColumnDetailsDto>> columns = seatConfigurationController.getColumns(1);
        
        System.out.println(columns);
        assertThat(columns.getData()).isEqualTo(listOfColumns.getData());
        
    }
    
    @Test
    void testGetColumns_Return_Empty() {
        
        Mockito.mock(SeatConfigurationService.class);
        
        Mockito.when(seatConfigurationService.getAllColumns(1)).thenReturn(Collections.emptyList());
        
        GenericResponse<List<ColumnDetailsDto>> columns = seatConfigurationController.getColumns(1);
        
        assertThat(columns.getData()).isEmpty();
    }

    @Test
    void testGetSeats_WithColumnId_ReturnsSeatNumberDtoList() {
        
        Mockito.mock(SeatNumberDto.class);
        Mockito.mock(SeatConfigurationController.class);
        
        //ColumnDetailsDto column1 = new ColumnDetailsDto(1);
        
        seatNumberDto = Arrays.asList( new SeatNumberDto(2, 1, true, true),
                new SeatNumberDto(3, 2, true, true), new SeatNumberDto(4, 3, true, true), new SeatNumberDto(5, 4, true, true),
                new SeatNumberDto(6, 5, true, true), new SeatNumberDto(7, 6, true, true), new SeatNumberDto(8, 7, true, true),
                new SeatNumberDto(9, 8, true, true), new SeatNumberDto(10, 9, true, true), new SeatNumberDto(11, 10, true, true));
        
        listOfSeat = new GenericResponse<List<SeatNumberDto>>(seatNumberDto, null);
        
        Mockito.when(seatConfigurationService.getAllSeats(1)).thenReturn(seatNumberDto);
        
        GenericResponse<List<SeatNumberDto>> seats = seatConfigurationController.getSeats(1);
        
        System.out.println(seats);
        
        assertThat(seats.getData()).isEqualTo(listOfSeat.getData());
    }
    
    @Test
    void testGetSeats_Return_Empty() {
        
        Mockito.mock(SeatConfigurationService.class);
        
        Mockito.when(seatConfigurationService.getAllSeats(1)).thenReturn(Collections.emptyList());
        
        GenericResponse<List<SeatNumberDto>> seats = seatConfigurationController.getSeats(1);
        
        assertThat(seats.getData()).isEmpty();
    }

}