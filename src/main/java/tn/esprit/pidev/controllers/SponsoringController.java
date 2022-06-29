package tn.esprit.pidev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.rest.api.v2010.account.Message;

import tn.esprit.pidev.entities.Promotion;
import tn.esprit.pidev.entities.Sponsoring;
import tn.esprit.pidev.repositories.ISponsoringRepository;
import tn.esprit.pidev.services.ISponsoringService;
@RestController
public class SponsoringController {
	@Autowired
	ISponsoringService iSponsoringService;
	@PostMapping("/sponsoring/add-sponsoring/{idevent}")  
	@ResponseBody()
    public String sponsoringEvent(@PathVariable("idevent") int idevent,
    		@PathVariable("iduser") int iduser,@RequestBody Sponsoring sponsoring
		)   
	{  
		return iSponsoringService.sponsoringEvent(idevent,iduser,sponsoring);
	}
	
	@GetMapping("/sponsoring/verify-demande/{iduser}/{idevent}")
	@ResponseBody
	public ResponseEntity<?> verifySponsoringDemande(@PathVariable("iduser")int iduser,
			@PathVariable("idevent")int idevent) {
		return iSponsoringService.verifySponsoringDemande(iduser,idevent);
		}
	
	
	}
