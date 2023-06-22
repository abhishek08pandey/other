package com.onerivet.deskbook.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "[Column]", schema = "[Ref]")
public class ColumnDetails {
	@Id
	@Column(name = "ColumnId")
	private int id;
	
	@Column(name = "ColumnName")
	private String columnName;
	
	@OneToOne
	@JoinColumn(name = "FloorId")
	private Floor floor;

	public ColumnDetails(int id) {
		super();
		this.id = id;
	}
	
}
