package com.onerivet.deskbook.models.entity;


import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "[SeatConfiguration]", schema = "[dbo]")
public class SeatConfiguration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SeatConfigurationId")
	private int id;
	
	@OneToOne
	@JoinColumn(name = "SeatId")
	private SeatNumber seatNumber;
	
	@OneToOne
	@JoinColumn(name = "EmployeeId")
	private Employee employee;
	
	@OneToOne
	@JoinColumn(name = "CreatedBy")
	private Employee createdBy;
	
	@Column(name = "ModifiedDate")
	private LocalDateTime modifiedDate;
	
	@OneToOne
	@JoinColumn(name = "ModifiedBy")
	private Employee modifiedBy;
	
	@Column(name = "DeletedDate")
	private LocalDateTime deletedDate;
	
	@OneToOne
	@JoinColumn(name = "DeletedBy")
	private Employee deletedBy;
	
	
}
