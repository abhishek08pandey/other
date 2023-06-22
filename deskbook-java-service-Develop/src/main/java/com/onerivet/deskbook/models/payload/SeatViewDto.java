package com.onerivet.deskbook.models.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NotEmpty
@ToString
public class SeatViewDto {
	
	private SeatNumberDto seat;
	
	private ColumnDetailsDto column;
	
	private String status;

}
