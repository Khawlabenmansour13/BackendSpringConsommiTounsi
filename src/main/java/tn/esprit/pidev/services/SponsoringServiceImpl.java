package tn.esprit.pidev.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import tn.esprit.pidev.entities.Donation;
import tn.esprit.pidev.entities.Event;
import tn.esprit.pidev.entities.Jackpot;
import tn.esprit.pidev.entities.Participation;
import tn.esprit.pidev.entities.Roletype;
import tn.esprit.pidev.entities.Sponsor;
import tn.esprit.pidev.entities.Sponsoring;
import tn.esprit.pidev.entities.SponsoringPk;
import tn.esprit.pidev.entities.Stock;
import tn.esprit.pidev.entities.User;
import tn.esprit.pidev.repositories.IDonationRepository;
import tn.esprit.pidev.repositories.IEventRepository;
import tn.esprit.pidev.repositories.IJackpotRepository;
import tn.esprit.pidev.repositories.ISponsorRepository;
import tn.esprit.pidev.repositories.ISponsoringRepository;
import tn.esprit.pidev.repositories.IStockRepository;
import tn.esprit.pidev.repositories.IUserRepository;

@Service
public class SponsoringServiceImpl  implements ISponsoringService {
	  
	private static final int MAXIMUM_AMOUNT = 100;
	
	private static final int TOTAL_NUMBER_DEMANDE = 3;
		@Autowired
		IEventRepository iEventRepository;
		@Autowired
		IJackpotRepository iJackpotRepository;
		@Autowired
		ISponsoringRepository iSponsoringRepository;
		@Autowired
		IUserRepository iUserRepository;
	
		
	private final static String ACCOUNT_SID = "AC41fe741cf763ba5111f1584df033d3a9";
	private final static String AUTH_ID = "d6046ecbb21fba15ec5583a9f011a960";
	
	
	private final static String TWILIO_NUMBER ="14086281602";

	@Override
	public List<Sponsoring> getSponsoringEvent(Event event) {
		List<Sponsoring> list = (List<Sponsoring>) iSponsoringRepository.findAll();
		return list;
	}

	@Override
	public List<Sponsor> getHistorySponsoring() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ResponseEntity<?> verifySponsoringDemande(int iduser,int idevent ) {
		
		System.out.println("hii");
		
		Sponsoring sponsoring = new Sponsoring();
        SponsoringPk sponsoringPk = new SponsoringPk(iduser,idevent);

		
        int ev = sponsoringPk.getIdEvent();
        int  sp = sponsoringPk.getIduser();
		Event event = iEventRepository.findById(ev).get();
		User sponsor = iUserRepository.findById(sp).get();
		sponsoring = iSponsoringRepository.findSponsoring(event,(Sponsor) sponsor); 
		System.out.println("sponsoring =="+sponsoring.toString());
	
		//sponsoringPk.setIduser(iduser);
		//sponsoringPk.setIdEvent(event.getIdEvent());
		sponsoring.setSponsoringPk(sponsoringPk);
		
		
		float totale  = sponsoring.getPriceProduct() * sponsoring.getProductQuantity() ;
		 System.out.println("result ="+totale);
		 
		 if(MAXIMUM_AMOUNT <= totale )
		 { 
			 sponsoring.setStatus("CONFIRME");
			 iSponsoringRepository.save(sponsoring);
			 event.setStatus(true);
			 iEventRepository.save(event);

			 return ResponseEntity.ok(sponsoring.getStatus());

		 }
				 
		 else {
			 sponsoring.setStatus("REJECTED");
			 iSponsoringRepository.save(sponsoring);
			 event.setStatus(false);
			 return ResponseEntity.ok(sponsoring.getStatus());
		 }

	}
	
	
	@Override
	public String sponsoringEvent(int eid, int uid,Sponsoring sponsoring) {
		Twilio.init(ACCOUNT_SID, AUTH_ID);
		
		Message message;
		float totale=0;
		float newCollAmount=0;
		Event ev = iEventRepository.findById(eid).get();
		User user = iUserRepository.findById(uid).get();
		Date date = new Date();
		
		SponsoringPk sponsoringPk = new SponsoringPk();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		User admin = iUserRepository.findAdmin();
		if(user.getRole().getType().equals(Roletype.SPONSOR)) {
			
		if(date.getTime() - ev.getDate().getTime() >0)
			return "Event is not available for sponsoring ";
	
			sponsoring.setDateSponsoring(LocalDateTime.now());
			sponsoring.setStatus("En Cours");
			sponsoringPk.setIduser(user.getIduser());
			sponsoringPk.setIdEvent(ev.getIdEvent());
			sponsoring.setSponsoringPk(sponsoringPk);
			iSponsoringRepository.save(sponsoring);
			System.out.println("COUNT ="+iSponsoringRepository.countNumberOfRequest(
					 sponsoringPk.getIduser(), sponsoringPk.getIdEvent()));
			 if(iSponsoringRepository.countNumberOfRequest(
					 sponsoringPk.getIduser(), sponsoringPk.getIdEvent())
					 >=3)
		{ 
				 sponsoring.setStatus("REJECTED");
				 iSponsoringRepository.save(sponsoring);
				 return "Please check your number request you  cross the limit which is 3 Totaly Requests";
		}
			
			  Message.creator(
				    new PhoneNumber(String.valueOf(admin.getTel())),
				    new PhoneNumber(TWILIO_NUMBER),
				    "Hello, Sponsor"+user.getFirstname()+"demand a sponsoring for product "+sponsoring.getProductName()+" with Quantity "+sponsoring.getProductQuantity()+" by price :"+sponsoring.getPriceProduct()+" for event : "+ev.getTitle())
				.create();
			  JSONArray productJsonArray = new JSONArray();
	    		try {
		    		JSONObject productJson = new JSONObject("{}");

				productJson.put("Request sponsoring sent to Admin","pending");
				productJson.put("Sponsring id event",sponsoringPk.getIdEvent());

				productJson.put("Added by","Admin");
		     	 productJsonArray.put(productJson);
		     	 return productJsonArray.toString();

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
			
		
		}
		
		return null;
	}

	


}
	
	

