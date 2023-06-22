package com.onerivet.deskbook.models.payload;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onerivet.deskbook.models.entity.Employee;
import com.onerivet.deskbook.models.entity.SeatNumber;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SeatRequestDto {

	private int id;
	
	@JsonIgnore
	private Employee employee;
	
	@JsonIgnore
	private SeatNumber seatId;
	
	private LocalDateTime createdDate;
	
	private LocalDate bookingDate;
	
	private String reason;
	
	private int requestStatus;
	
	private LocalDateTime modifiedDate;
	
	@JsonIgnore
	private Employee modifiedBy;
	
	private LocalDateTime deletedDate;
	
	@JsonIgnore
	private Employee deletedBy;
}