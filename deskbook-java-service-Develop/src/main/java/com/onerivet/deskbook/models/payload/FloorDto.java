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
public class FloorDto {
	private int id;

	private String name;

	public FloorDto(int id) {
		super();
		this.id = id;
	}
	
}
