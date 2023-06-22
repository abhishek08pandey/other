package com.onerivet.deskbook.controllers;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.deskbook.models.payload.RequestHistoryDto;
import com.onerivet.deskbook.models.response.GenericResponse;
import com.onerivet.deskbook.services.RequestHistoryService;
import com.onerivet.deskbook.util.PaginationAndSorting;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@Validated
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/deskbook")
public class RequestHistoryController {
    
    
    @Autowired
    private RequestHistoryService requestHistoryService;
    
    final static Logger logger = LoggerFactory.getLogger(RequestHistoryController.class);
    
    public RequestHistoryController(RequestHistoryService requestHistoryService) {
        super();
        this.requestHistoryService = requestHistoryService;
    }

    @GetMapping(value = { "/request-history/{requestStatus}","/request-history/"})
    public GenericResponse<List<RequestHistoryDto>>  getReqestHistory(Principal principal,  PaginationAndSorting pagination, @PathVariable(required = false) Integer requestStatus){
    	logger.info("[getReqestHistory()] started");
        GenericResponse<List<RequestHistoryDto>> genericResponse = new GenericResponse<>(this.requestHistoryService.getRequestHistory(principal.getName(),pagination.createPageRequest(), requestStatus), null);
        logger.info("[getReqestHistory()] ended");
        return genericResponse;
        
        
    }
    
    

    @GetMapping("/request-history/search/{name}")
     public GenericResponse<List<RequestHistoryDto>> getAllbyFirstNameandLastName(Principal principal,@PathVariable("name") String name,PaginationAndSorting sorting) {
    	logger.info("[getAllbyFirstNameandLastName()] started");
    	GenericResponse<List<RequestHistoryDto>> genericResponse = new GenericResponse<>(this.requestHistoryService.searchByFirstNameOrLastName(principal.getName(),name,sorting.createPageRequest()), null);
    	logger.info("[getAllbyFirstNameandLastName()] ended");
    	return genericResponse;
    
    }
    
//    @GetMapping("/take-action")
//	public GenericResponse<RequestHistoryTakeActionDto> requestHistoryTakeAction(@RequestBody RequestHistoryDto requestHistoryDto){
//		GenericResponse<RequestHistoryTakeActionDto> genericResponse=new GenericResponse<>(this.requestHistoryService.takeAction(requestHistoryDto),null);
//		return genericResponse;
//	}
}