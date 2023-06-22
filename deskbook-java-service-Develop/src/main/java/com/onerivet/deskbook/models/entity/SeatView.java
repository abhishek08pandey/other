package com.onerivet.deskbook.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SeatView {
	
    private SeatNumber seat;
    
    private ColumnDetails column;
	
	private String status;
}
