package com.onerivet.deskbook.models.entity;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Builder
@ToString
@Entity
@Table(name = "[EmployeeWorkingDays]", schema = "[dbo]")
public class EmployeeWorkingDays {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EmployeeWorkingDayId")
	private int id;
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EmployeeId")
	private Employee employee;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "WorkingDayId")
	private WorkingDay day;
	
	@OneToOne
	@JoinColumn(name = "CreatedBy")
	private Employee createdBy;
	
	@OneToOne
	@JoinColumn(name = "ModifiedBy")
	private Employee modifiedBy;
	
	@Column(name = "ModifiedDate")
	private LocalDateTime modifiedDate;
	
	@OneToOne
	@JoinColumn(name = "DeletedBy")
	private Employee deletedBy;
	
	@Column(name = "DeletedDate")
	private LocalDateTime deletedDate;

	public EmployeeWorkingDays(Employee employee, WorkingDay day, Employee createdBy, Employee modifiedBy,
			LocalDateTime modifiedDate) {
		super();
		this.employee = employee;
		this.day = day;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}
	
}
