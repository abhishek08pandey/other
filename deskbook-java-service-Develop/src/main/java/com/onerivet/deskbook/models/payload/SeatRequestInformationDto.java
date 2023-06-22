package com.onerivet.deskbook.models.payload;

import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatRequestInformationDto {
	
	@NotEmpty(message = "Reason is mandatory.")
	@Size(max=200, message = "Exceeded maximum character limit of 200")
	private String reason;
	
	@JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate requestDateTime;
	
	@NotNull
    private Integer seatId;
}
