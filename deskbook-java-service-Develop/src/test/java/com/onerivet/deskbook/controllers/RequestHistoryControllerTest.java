package com.onerivet.deskbook.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import com.onerivet.deskbook.models.payload.RequestHistoryDto;
import com.onerivet.deskbook.models.response.GenericResponse;
import com.onerivet.deskbook.services.RequestHistoryService;
import com.onerivet.deskbook.util.PaginationAndSorting;

@ExtendWith(MockitoExtension.class)
public class RequestHistoryControllerTest {
	
	@Mock
    private RequestHistoryService requestHistoryService;

    @InjectMocks
    private RequestHistoryController requestHistoryController;
    
    
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetReqestHistory() {
        Principal principal = () -> "testUser";
        PaginationAndSorting pagination = new PaginationAndSorting();
        Integer requestStatus = 1;
        
        List<RequestHistoryDto> requestHistoryList = new ArrayList<>();

        RequestHistoryDto request1 = new RequestHistoryDto();
        request1.setSeatId(1);
        request1.setName("Deep Patel");
        request1.setRequestDate(LocalDateTime.now());
        request1.setRequestFor(LocalDate.now());
        request1.setEmailId("deep@gmail.com");
        request1.setFloorNo(1);
        request1.setDeskNo("A1");
        request1.setStatus(1);
        request1.setReason("last day");
        requestHistoryList.add(request1);
               
        RequestHistoryDto request2 = new RequestHistoryDto();
        request2.setSeatId(2);
        request2.setName("Jane Smith");
        request2.setRequestDate(LocalDateTime.now());
        request2.setRequestFor(LocalDate.now());
        request2.setEmailId("janesmith@example.com");
        request2.setFloorNo(2);
        request2.setDeskNo("B2");
        request2.setStatus(2);
        request2.setReason("last day");
        requestHistoryList.add(request2);
               

        when(requestHistoryService.getRequestHistory(principal.getName(), PageRequest.of(0, 10), requestStatus))
                .thenReturn(requestHistoryList);

     
        GenericResponse<List<RequestHistoryDto>> response = requestHistoryController.getReqestHistory(principal, pagination, requestStatus);

       
        assertEquals(requestHistoryList, response.getData());
        assertEquals(null, response.getError());
    }
    
    
   
    @Test
    public void testGetAllbyFirstNameandLastName() {
        String name = "John Doe";
        PaginationAndSorting sorting = new PaginationAndSorting();
        List<RequestHistoryDto> requestHistoryList = new ArrayList<>();
        
        RequestHistoryDto request1 = new RequestHistoryDto();
        request1.setSeatId(1);
        request1.setName("Deep Patel");
       

        RequestHistoryDto request2 = new RequestHistoryDto();
        request2.setSeatId(2);
        request2.setName("Meet Smith");
       

        requestHistoryList.add(request1);
        requestHistoryList.add(request2);
        Principal principal = () -> "testUser";

       
        when(requestHistoryService.searchByFirstNameOrLastName(principal.getName(),name, sorting.createPageRequest()))
                .thenReturn(requestHistoryList);

       
        GenericResponse<List<RequestHistoryDto>> response = requestHistoryController.getAllbyFirstNameandLastName(principal,name, sorting);

       
        assertEquals(requestHistoryList, response.getData());
        assertEquals(null, response.getError());
    }

   
    
    

}
