package com.onerivet.deskbook.services;

import java.util.List;

import com.onerivet.deskbook.models.payload.ColumnDetailsDto;
import com.onerivet.deskbook.models.payload.FloorDto;
import com.onerivet.deskbook.models.payload.SeatNumberDto;

public interface SeatConfigurationService {

	public List<FloorDto> getAllFloors(int cityId);

	public List<ColumnDetailsDto> getAllColumns(int floorId);

	public List<SeatNumberDto> getAllSeats(int columnId);
}
