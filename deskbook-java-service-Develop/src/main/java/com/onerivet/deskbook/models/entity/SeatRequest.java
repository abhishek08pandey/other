package com.onerivet.deskbook.models.entity;

import java.time.LocalDate;
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

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "[SeatRequest]", schema = "[Booking]")
public class SeatRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SeatRequestId")
    private int id;

    @OneToOne
    @JoinColumn(name = "EmployeeId")
    private Employee employee;

    @OneToOne
    @JoinColumn(name = "SeatId")
    private SeatNumber seat;

    @Column(name = "CreatedDate")
    private LocalDateTime createdDate;

    @Column(name = "BookingDate")
    private LocalDate bookingDate;

    @Column(name = "Reason")
    private String reason;

    @Column(name = "RequestStatus")
    private int requestStatus;

    @Column(name = "ModifiedDate")
    private LocalDateTime modifiedDate;

    @OneToOne
    @JoinColumn(name = "ModifiedBy")
    private Employee modifiedBy;

    @Column(name = "DeletedDate")
    private LocalDateTime deletedDate;


}