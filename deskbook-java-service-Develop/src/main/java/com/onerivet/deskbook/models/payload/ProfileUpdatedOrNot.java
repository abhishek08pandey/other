package com.onerivet.deskbook.models.payload;

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
public class ProfileUpdatedOrNot {
    
    private boolean isUpdated;
    
    private String ProfilePictureFileString;
    
    private boolean isActive;
   
}