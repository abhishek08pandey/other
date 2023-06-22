package com.onerivet.deskbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onerivet.deskbook.models.entity.Employee;
import com.onerivet.deskbook.models.entity.EmployeeWorkingDays;

@Repository
public interface EmployeeWorkingDaysRepo extends JpaRepository<EmployeeWorkingDays, Integer> {
	public List<EmployeeWorkingDays> findByEmployee(Employee employee);
}
