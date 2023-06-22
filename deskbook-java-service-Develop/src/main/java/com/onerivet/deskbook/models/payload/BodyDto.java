package com.onerivet.deskbook.models.payload;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BodyDto {

	private String employeeName;

	private LocalDate bookingDate;
	private String city;
	private String floorName;
	private int seatNumber;
	private String duration;

	public BodyDto(String employeeName) {
		super();
		this.employeeName = employeeName;
	}

	public BodyDto(String employeeName, LocalDate bookingDate) {
		super();
		this.employeeName = employeeName;
		this.bookingDate = bookingDate;
	}

}