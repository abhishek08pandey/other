package com.onerivet.deskbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onerivet.deskbook.models.entity.WorkingDay;

@Repository
public interface WorkingDaysRepo extends JpaRepository<WorkingDay, Integer> {

}
