package com.onerivet.deskbook.models.payload;

import java.util.Set;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProfileViewDto {
	

	private String profilePictureFileString;

	private String emailId;

	private String firstName;

	private String lastName;

	private String phoneNumber;

	private DesignationDto designation;
	
	private ModeOfWorkDto modeOfWork;


	private CityDto city;

	private FloorDto floor;

	private ColumnDetailsDto column;

	private SeatNumberDto seat;
	
	private Set<WorkingDayDto> days;
	
	private boolean isActive;

}
