package com.onerivet.deskbook.repository;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.onerivet.deskbook.models.entity.City;
import com.onerivet.deskbook.models.entity.ColumnDetails;
import com.onerivet.deskbook.models.entity.Designation;
import com.onerivet.deskbook.models.entity.Employee;
import com.onerivet.deskbook.models.entity.Floor;
import com.onerivet.deskbook.models.entity.SeatConfiguration;
import com.onerivet.deskbook.models.entity.SeatNumber;

@ExtendWith(MockitoExtension.class)
class SeatConfigurationRepoTest {
    @Mock
    private SeatConfigurationRepo seatConfigurationRepo;
    private Employee employee;
    private SeatNumber seatNumber;
    private ColumnDetails columnDetails;
    private Floor floor;
    private City city;
    @Mock
    private SeatConfiguration seatConfiguration;
    @BeforeEach
    void setUp() throws Exception {
        city = new City();
        city.setId(2);
        city.setCityName("Valsad");
        floor = new Floor();
        floor.setId(2);
        floor.setFloorName("1st Floor");
        floor.setCity(city);
        columnDetails = new ColumnDetails();
        columnDetails.setId(2);
        columnDetails.setColumnName("A");
        columnDetails.setFloor(floor);
        seatNumber = new SeatNumber();
        seatNumber.setId(2);
        seatNumber.setColumn(columnDetails);
        seatNumber.setSeatNumber(1);
        employee = new Employee();
        employee.setId("00b2a3ba-3aa2-457a-920b-e6c395f02273");
        employee.setFirstName("abc");
        employee.setLastName("xyz");
        employee.setDesignation(new Designation());
        employee.setPhoneNumber("9875643211");
        employee.setEmailId("abc@gmail.com");
        employee.setProfilePictureFileName("hgdjkwbdljwhidj");
        employee.setProfilePictureFilePath("dguqiwteui");
        
        seatConfiguration=new SeatConfiguration();
        seatConfiguration.setId(2);
        seatConfiguration.setSeatNumber(seatNumber);
        seatConfiguration.setEmployee(employee);
        seatConfiguration.setCreatedBy(employee);
        seatConfiguration.setModifiedDate(null);
        seatConfiguration.setModifiedBy(employee);
    }
    @Test
    void testFindSeats_WithSeatNumberList_ReturnsSeatNumberList() {
        List<SeatNumber> seatNumberList = new ArrayList<>();
        seatNumberList.add(seatNumber);
        when(seatConfigurationRepo.findSeats(seatNumberList)).thenReturn(seatNumberList);
        List<SeatNumber> result = seatConfigurationRepo.findSeats(seatNumberList);
        System.out.println(result);
       assertEquals(seatNumberList,result);
        
    }
    @Test
    void testFindBySeatNumber_WithSeatNumber_ReturnsSeatConfiguration() {
        when(seatConfigurationRepo.findBySeatNumberAndDeletedByNull(seatNumber)).thenReturn(seatConfiguration);
        SeatConfiguration result = seatConfigurationRepo.findBySeatNumberAndDeletedByNull(seatNumber);
        System.out.println(result);
        assertNotNull(result);
    }
    @Test
    void testFindByEmployee_WithEmployee_ReturnsSeatConfiguration() {
        when(seatConfigurationRepo.findByEmployee(employee)).thenReturn(seatConfiguration);
        SeatConfiguration result = seatConfigurationRepo.findByEmployee(employee);
        System.out.println(result);
        assertNotNull(result);
    }
}
