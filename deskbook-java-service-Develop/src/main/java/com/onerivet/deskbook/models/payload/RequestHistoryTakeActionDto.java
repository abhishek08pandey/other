package com.onerivet.deskbook.models.payload;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestHistoryTakeActionDto {

    private int seatRequestId ;
	
	private String employeeId;
    
	private String name;
	
	private LocalDateTime requestDate;
	
	private  LocalDate requestFor;
	
	private String emailId;
	
	private int floorNo;
	
	private String deskNo;
	
	private int status;
	
	private String reason;
}
