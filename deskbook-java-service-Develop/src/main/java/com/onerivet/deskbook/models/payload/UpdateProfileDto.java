package com.onerivet.deskbook.models.payload;


import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class UpdateProfileDto {
	
	private String profilePictureFileString;
	
	@NotEmpty(message = "Please enter your Name")
    @Size(min = 2, message = "First name must be of 2 characters or more")
    @Size(max = 100, message = "Your first name cannot be exceed 100 characters")
    @Pattern(regexp = "^[A-Za-z]+'?[a-zA-Z]+$", message = "Please enter valid first name")
    private String firstName;

    @NotEmpty(message = "Please enter your Name")
    @Size(min = 2, message = "Last name must be of 2 characters or more")
    @Size(max = 100, message = "Your Last name cannot be exceed 100 characters")
    @Pattern(regexp = "^[A-Za-z]+'?[a-zA-Z]+$", message = "Please enter valid Last name")
    private String lastName;

    @Pattern(regexp = "^[0-9]*$", message = "Please enter a numeric value only")
    @Size(min = 10, max = 10, message = "10-digit number is required")
    @NotEmpty(message = "Please enter phone no.")
    private String phoneNumber;
	
	@NotNull(message = "Please select designation")
	private Integer designation;

	@NotNull(message = "Please select Mode of work")
	private Integer modeOfWork;
	 

	private Integer city;
	
	private Integer floor;
	
	private Integer column;
	
	private Integer seat;
	
	private Set<Integer> workingDays;

}
