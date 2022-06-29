package tn.esprit.pidev.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.twilio.rest.api.v2010.account.Message;

import tn.esprit.pidev.entities.Donation;
import tn.esprit.pidev.entities.Event;
import tn.esprit.pidev.entities.Sponsor;
import tn.esprit.pidev.entities.Sponsoring;

public interface ISponsoringService {
	
	public List<Sponsoring> getSponsoringEvent(Event event) ;
	public List<Sponsor> getHistorySponsoring();
	public ResponseEntity<?> verifySponsoringDemande(int iduser,int idevent);
	String sponsoringEvent(int eid, int uid, Sponsoring sponsoring);
	

}
