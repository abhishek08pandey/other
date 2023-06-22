package com.onerivet.deskbook.repository;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.onerivet.deskbook.models.entity.Employee;
import com.onerivet.deskbook.models.entity.EmployeeWorkingDays;

@ExtendWith(MockitoExtension.class)
class EmployeeWorkingDaysRepoTest {

	@Mock
	private EmployeeWorkingDaysRepo employeeWorkingDaysRepo;

	@Mock
	Employee employee;

	@Test
	void testFindByEmployee_WithFindWorkingDays_ReturnsListOfWorkingDays() {

		employee.setId("1");

		EmployeeWorkingDays workingDays1 = new EmployeeWorkingDays();
		workingDays1.setId(1);
		workingDays1.setEmployee(employee);

		EmployeeWorkingDays workingDays2 = new EmployeeWorkingDays();
		workingDays2.setId(2);
		workingDays2.setEmployee(employee);

		List<EmployeeWorkingDays> daysList = Arrays.asList(workingDays1, workingDays2);

		when(employeeWorkingDaysRepo.findByEmployee(employee)).thenReturn(daysList);

		List<EmployeeWorkingDays> foundWorkingDays = employeeWorkingDaysRepo.findByEmployee(employee);
		
		Assertions.assertThat(foundWorkingDays.size()).isEqualTo(2);
		Assertions.assertThat(foundWorkingDays).contains(workingDays1, workingDays2);

	}

	@Test
    void testFindByEmployee_WithFindWorkingDays_ReturnsEmptyList() {
    
        Employee employee = new Employee();
        employee.setId("1");

        when(employeeWorkingDaysRepo.findByEmployee(employee)).thenReturn(Collections.emptyList());
        
        List<EmployeeWorkingDays> foundWorkingDays = employeeWorkingDaysRepo.findByEmployee(employee);

        Assertions.assertThat(foundWorkingDays.isEmpty());
     
   }
}
