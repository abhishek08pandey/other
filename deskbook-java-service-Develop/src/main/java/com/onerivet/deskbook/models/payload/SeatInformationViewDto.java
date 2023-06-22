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
public class SeatInformationViewDto {
    
    private String name;
    private String designation;
    private String duration="FULL DAY";
    private String email;
    private int countOfRequest;
    private TemporarySeatOwnerDto temporarySeatOwnerDto;
    

}