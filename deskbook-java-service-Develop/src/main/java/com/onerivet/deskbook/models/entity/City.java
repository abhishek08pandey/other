package com.onerivet.deskbook.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "[City]", schema = "[Ref]")
public class City {
	@Id
	@Column(name = "CityId")
	private int id;
	
	@Column(name = "CityName")
	private String cityName;

	public City(int id) {
		super();
		this.id = id;
	}
	
}
