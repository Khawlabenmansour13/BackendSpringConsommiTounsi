package tn.esprit.pidev.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pidev.entities.Event;
import tn.esprit.pidev.entities.Jackpot;
import tn.esprit.pidev.services.IJackpotService;

@RestController
public class JackpotController {
@Autowired
IJackpotService iJackpotService;

//creating post mapping that post the jackpot detail in the database  
		@PostMapping("/jackpot/add-jackpot")  
		@ResponseBody
		private String addJackpot()   
		{  
			iJackpotService.addJackpot()  ;
			return "Jackpot addedd succefully!";  
		}
		
//creating a get mapping that retrieves all the event detail from the database   
		@GetMapping("/jackpot/get-all-jackpot")  
		public Jackpot findJackpot(Event event)  
		{  
			return iJackpotService.findJackpot(event);  
		} 
	
}
