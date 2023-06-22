package com.onerivet.deskbook.repository;


import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.onerivet.deskbook.models.entity.ColumnDetails;
import com.onerivet.deskbook.models.entity.Floor;

@ExtendWith(MockitoExtension.class)
class ColumnDetailsRepoTest {
    
    @Mock
    private ColumnDetailsRepo columnDetailsRepo;

    @Mock
    Floor floor;
    
    @Test
    void testFindByFloor_WithFindColumndetails_ReturnsListOfColumns() {
        
        floor.setId(1);
        
        ColumnDetails columndetails=new ColumnDetails();
        columndetails.setId(1);
        columndetails.setFloor(floor);
        
        ColumnDetails columndetails1=new ColumnDetails();
        columndetails1.setId(2);
        columndetails1.setFloor(floor);
        
        List<ColumnDetails> columnList=Arrays.asList(columndetails,columndetails1);
        
        when(columnDetailsRepo.findByFloor(floor)).thenReturn(columnList);
        
        List<ColumnDetails> foundColumn=columnDetailsRepo.findByFloor(floor);
        
        
        Assertions.assertThat(foundColumn.size()).isEqualTo(2);
        Assertions.assertThat(foundColumn).contains(columndetails,columndetails1);
    }
    @Test
    void testFindByFloor_WithFindColumndetails_ReturnsEmptyList() {
        floor.setId(1);
        when(columnDetailsRepo.findByFloor(floor)).thenReturn(Collections.emptyList());
        List<ColumnDetails> foundColumn=columnDetailsRepo.findByFloor(floor);
        Assertions.assertThat(foundColumn.isEmpty());
    }
    

}
