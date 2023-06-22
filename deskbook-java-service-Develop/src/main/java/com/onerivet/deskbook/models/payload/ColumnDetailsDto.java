package com.onerivet.deskbook.models.payload;


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
@ToString
@Builder
public class ColumnDetailsDto {
	private int id;

	private String name;

	public ColumnDetailsDto(int id) {
		super();
		this.id = id;
	}
	
}
