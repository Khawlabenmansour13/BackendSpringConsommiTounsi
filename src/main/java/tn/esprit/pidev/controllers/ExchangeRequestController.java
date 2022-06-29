package tn.esprit.pidev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pidev.services.ExchangeRequestServiceImpl;
import tn.esprit.pidev.services.IMeetingService;

@RestController
public class ExchangeRequestController {

	@Autowired
	private ExchangeRequestServiceImpl exchangeService;
	
	@Autowired
	private IMeetingService meetingServiceImpl;
	
	   

	    @PostMapping("/requestExchange/sendrequestExchange")
	    public String processExchangeRequest(@RequestParam("oldAppointmentId") int oldAppointmentId, @RequestParam("newAppointmentId") int newAppointmentId) throws Exception {
	     

	    	boolean result = exchangeService.requestExchange(oldAppointmentId, newAppointmentId );
	        if (result) {
	           return  "Exchange request sucsessfully sent!";
	        } else {
	            return  "Error! Exchange not sent!";
	        }
	    }
	        
	    @PostMapping("/accept")
	    public String processExchangeAcceptation(@RequestParam("exchangeId") int exchangeId) throws Exception {
	    	//User currentUser  = exchangeService.userConnected();
	        exchangeService.acceptExchange(exchangeId);
	        return "Exchange Request Accepted";
	    }

	    @PostMapping("/reject")
	    public String processExchangeRejection(@RequestParam("exchangeId") int exchangeId ) throws Exception{
	        exchangeService.rejectExchange(exchangeId);
	        return "Exchange Request Rejected";
	    }
}