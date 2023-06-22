package com.onerivet.deskbook.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.onerivet.deskbook.models.entity.Employee;
import com.onerivet.deskbook.models.entity.SeatNumber;
import com.onerivet.deskbook.models.entity.SeatRequest;

@Repository
public interface SeatRequestRepo extends JpaRepository<SeatRequest, Integer> {

	public int countFindBySeatAndBookingDateAndDeletedDateNull(SeatNumber seatId, LocalDate bookingdate);

	public SeatRequest findByEmployeeIdAndSeatAndBookingDateAndDeletedDateNull(String employeeId, SeatNumber seatId,
			LocalDate LocalbookingDate);

	@Query(value = "SELECT sr FROM SeatRequest sr WHERE sr.requestStatus=2 AND sr.bookingDate=:bookingDate AND sr.seat=:seatNumber AND sr.deletedDate IS NULL")
	public SeatRequest findByBookingDateAndSeatAndDeletedDateNull(LocalDate bookingDate, SeatNumber seatNumber);

	@Query(value = "SELECT sr FROM SeatRequest sr WHERE  sr.seat =:seat AND sr.requestStatus<>4 ORDER BY sr.createdDate DESC")//change DESC
	public List<SeatRequest> findSeatRequestBySeat(SeatNumber seat, Pageable pageable);
	

	@Query(value = "SELECT sr FROM SeatRequest sr WHERE  sr.seat =:seat AND sr.requestStatus=:requestStatus ORDER BY sr.createdDate DESC")
	public List<SeatRequest> findSeatRequestBySeatAndRequestStatus(SeatNumber seat, Pageable pageable,
			int requestStatus);

	@Query("SELECT sr FROM SeatRequest sr LEFT JOIN sr.employee e WHERE e.firstName LIKE CONCAT(:firstName, '%') OR e.lastName LIKE CONCAT(:lastName, '%')")
	public List<SeatRequest> getByFirstNameOrLastName(@Param("firstName") String firstname,
			@Param("lastName") String lastname, Pageable pageble);

	@Query(value = "SELECT s FROM SeatRequest s WHERE s.seat=?1 AND s.bookingDate=?2 AND s.employee=?3")
	public SeatRequest alreadyBookedOrNot(SeatNumber seat, LocalDate requestDate, Employee empId);

	@Query(value = "SELECT s FROM SeatRequest s WHERE s.employee=?1 ")
	public List<SeatRequest> findSeatsByEmployeeId(Employee employee);

	@Query(value = "SELECT COUNT(s.id) FROM SeatRequest s WHERE s.employee=?1 AND s.bookingDate=?2")
	public int findEmployeeRequestForDay(Employee employee, LocalDate localDate);

	
	@Query(value = "SELECT s FROM SeatRequest s WHERE  s.bookingDate=?1 AND s.employee=?2 AND s.requestStatus=2")
    public SeatRequest alreadyAccepted(LocalDate requestDate,Employee empId);
	
//	@Query("SELECT sr FROM SeatRequest sr WHERE sr.employee =:employee")
//	public List<SeatRequest> findSeatRequestByEmployee(Employee employee, Pageable pageable);

	@Query("SELECT sr FROM SeatRequest sr WHERE sr.employee =:employee ORDER BY sr.createdDate DESC")
	public List<SeatRequest> getBookingHistoryByEmployee(Employee employee, Pageable pageable);

	@Query("SELECT sr FROM SeatRequest sr WHERE sr.employee =:employee AND sr.requestStatus=:requestStatus ORDER BY sr.createdDate DESC")
	public List<SeatRequest> getBookingHistoryByEmployeeAndRequestStatus(Employee employee, Pageable pageable,
			int requestStatus);

	@Query("SELECT sr.seat  FROM SeatRequest sr  LEFT JOIN SeatConfiguration sc on sr.seat=sc.seatNumber where sc.employee=null and sr.employee=?1 and sc.deletedBy=null")
	public List<SeatNumber> findUnAssignedSeat(Employee employee);

	public SeatRequest findByRequestStatusAndEmployeeIdAndSeatIdAndBookingDateAndDeletedDateNull(int pending,
			String employee, SeatNumber seatId, LocalDate bookingDate);

	public List<SeatRequest> getByRequestStatusAndBookingDateAndSeatIdAndDeletedDateNull(int requestStatus,
			LocalDate bookingDate, SeatNumber seatId);

	public List<SeatRequest> findByEmployeeIdAndRequestStatusAndBookingDateAndDeletedDateNull(String employee,
			int requestStatus, LocalDate bookingDate);
	
	public List<SeatRequest> findByBookingDateAndRequestStatusAndDeletedDateNull(LocalDate bookingDate, int requestStatus, Sort sort);

	public SeatRequest findByRequestStatusAndEmployeeIdAndSeatIdAndBookingDateAndDeletedDateNull(int pending,
			String employee, int seatId, LocalDate bookingDate);
	
	public List<SeatRequest> getByRequestStatusAndBookingDateAndSeatIdAndDeletedDateNull(int requestStatus,
			LocalDate bookingDate, int seatId);

}
