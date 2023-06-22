package com.onerivet.deskbook.models.payload;

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
public class SeatNumberDto {
	private int id;

	private int seatNumber;
	
	private boolean booked;
	
	private boolean isAvailable;

	public SeatNumberDto(int id) {
		super();
		this.id = id;
	}
	
	
}
