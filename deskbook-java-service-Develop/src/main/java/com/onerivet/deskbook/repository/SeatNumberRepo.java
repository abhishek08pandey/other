package com.onerivet.deskbook.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.onerivet.deskbook.models.entity.City;
import com.onerivet.deskbook.models.entity.ColumnDetails;
import com.onerivet.deskbook.models.entity.Floor;
import com.onerivet.deskbook.models.entity.SeatNumber;
import com.onerivet.deskbook.models.entity.SeatView;
import com.onerivet.deskbook.models.entity.WorkingDay;

@Repository
public interface SeatNumberRepo extends JpaRepository<SeatNumber, Integer> {
	public List<SeatNumber> findByColumn(ColumnDetails column);
	
	@Query(value = "SELECT s.column Column FROM SeatNumber s  INNER JOIN s.column c INNER JOIN c.floor f INNER JOIN f.city ct WHERE s.id=:id")
	public Map<String, ColumnDetails> findColumnFloorCityBySeat(Integer id);

	@Query(value ="SELECT NEW com.onerivet.deskbook.models.entity.SeatView(s, c,\n"
			+ "    CASE\n"
			+ "        WHEN s.isAvailable = 0 THEN 'Unavailable'\n"
			+ "        WHEN d.designationName IN ('ADMIN', 'INFRA', 'HR', 'DEVOPS', 'ACCOUNTS') THEN 'Reserved'\n"
			+ "        WHEN m.modeOfWorkName='Regular' THEN 'Booked'\n"
			+ "        WHEN ewd.day=:workingDay THEN 'Booked'\n"
			+ "        WHEN sr.requestStatus=2 THEN 'Booked'\n"
			+ "        WHEN e.id IS NULL THEN 'Unassigned'\n"
			+ "        ELSE 'Available'\n"
			+ "    END)\n"
			+ "FROM SeatNumber s\n"
			+ "LEFT JOIN SeatConfiguration sc ON sc.seatNumber = s AND sc.deletedDate IS NULL\n"
			+ "LEFT JOIN EmployeeWorkingDays ewd ON sc.employee = ewd.employee AND ewd.day = :workingDay AND ewd.deletedDate IS NULL\n"
			+ "LEFT JOIN sc.employee e\n"
			+ "LEFT JOIN s.column c\n"
			+ "LEFT JOIN c.floor f\n"
			+ "LEFT JOIN f.city ct\n"
			+ "LEFT JOIN WorkingDay wd ON ewd.day=wd\n"
			+ "LEFT JOIN e.modeOfWork m\n"
			+ "LEFT JOIN e.designation d\n"
			+ "LEFT JOIN SeatRequest sr ON sr.seat = s AND sr.bookingDate =:date AND sr.requestStatus = 2 AND sr.deletedDate IS NULL\n"
			+ "WHERE f = :floor AND ct = :city ORDER BY s\n")
	public List<SeatView> getViewByDateAndCityAndFloorAndWorkingDay(LocalDate date, City city, Floor floor, WorkingDay workingDay);
}
