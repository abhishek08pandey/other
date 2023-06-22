package com.onerivet.deskbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.onerivet.deskbook.models.entity.Employee;
import com.onerivet.deskbook.models.entity.SeatConfiguration;
import com.onerivet.deskbook.models.entity.SeatNumber;

@Repository
public interface SeatConfigurationRepo extends JpaRepository<SeatConfiguration, Integer> {

	@Query(value = "SELECT s.seatNumber FROM SeatConfiguration s WHERE s.seatNumber IN (:seats) And s.deletedBy = null")
	public List<SeatNumber> findSeats(List<SeatNumber> seats);

	public SeatConfiguration findBySeatNumberAndDeletedByNull(SeatNumber seat);

	public SeatConfiguration findByEmployeeAndDeletedByNull(Employee employee);
	
	public SeatConfiguration findByEmployee(Employee employee);

}
