package tn.esprit.pidev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pidev.entities.Donation;
import tn.esprit.pidev.entities.Event;
import tn.esprit.pidev.services.IDonationService;
import tn.esprit.pidev.services.IEventService;

@RestController
public class DonationController {
	@Autowired
	IDonationService iDonationService;
	
	@Autowired
	IEventService iEventService;
	//creating post mapping  
	@PostMapping("/event/donation-event/{eventid}/{uid}/{amount}")  
	private ResponseEntity<String> DonationOfEvent(@PathVariable("eventid")int eventid,
			 @PathVariable("uid")int uid,@PathVariable("amount")float amount )   
	{  
		String result = iDonationService.Donation(eventid,uid,amount);  
	    return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	

	@GetMapping("/event/displayDonationsEvent/{idevent}")
	public List<Donation>getDonationEvention(@PathVariable("idevent")int idevent) {
		
		return iDonationService.getDonationEvention(iEventService.findbyId(idevent));
	}
}