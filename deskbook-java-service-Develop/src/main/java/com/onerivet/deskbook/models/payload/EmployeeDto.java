package com.onerivet.deskbook.models.payload;


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
public class EmployeeDto {
	private String id;

	private String emailId;

	private String firstName;

	private String lastName;

	private String phoneNumber;

	private String profilePictureFileName;
	
	private String profilePictureFilePath;
	
	private ModeOfWorkDto modeOfWork;

	private DesignationDto designation;
	
	private boolean isActive;

}
