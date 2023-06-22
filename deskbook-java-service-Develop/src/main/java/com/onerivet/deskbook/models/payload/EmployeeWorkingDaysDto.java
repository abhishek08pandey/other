package com.onerivet.deskbook.models.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onerivet.deskbook.models.entity.Employee;
import com.onerivet.deskbook.models.entity.WorkingDay;

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
public class EmployeeWorkingDaysDto {
	
	private int id;

	@JsonIgnore
	private Employee employee;

	private WorkingDay day;

	@JsonIgnore
	private Employee createdBy;
}
