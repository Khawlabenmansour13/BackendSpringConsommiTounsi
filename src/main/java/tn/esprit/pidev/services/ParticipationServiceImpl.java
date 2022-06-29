package tn.esprit.pidev.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pidev.entities.Event;
import tn.esprit.pidev.entities.Participation;
import tn.esprit.pidev.entities.ParticipationPK;
import tn.esprit.pidev.entities.Roletype;
import tn.esprit.pidev.entities.User;
import tn.esprit.pidev.repositories.IEventRepository;
import tn.esprit.pidev.repositories.IParticipationRepository;
import tn.esprit.pidev.repositories.IUserRepository;
@Service
public class ParticipationServiceImpl implements IParticipationService{
	@Autowired
	EventServiceImpl eventServiceImpl;
	@Autowired
	IParticipationRepository iParticipationRepository;
	@Autowired
	IEventRepository iEventRepository;
	@Autowired
	IUserRepository iUserRepository;

@Override
public String addParticipation(int iduser, int idevent) {
		
		Event event = iEventRepository.findById(idevent).get();
		User user = iUserRepository.findById(iduser).get();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		int number = 0;
		Participation p = new Participation();
		ParticipationPK participationPK = new ParticipationPK();
		List<Participation>participations = (List<Participation>) iParticipationRepository.findAll();
		Date currentdate = new Date();
		System.out.println(user.getRole().getType());
		if(user.getRole().getType().equals(Roletype.SPONSOR) ||user.getRole().equals(Roletype.ADMIN)) 
				return "Sponsor or Admin couldn't  participate to any event  we are so sorry ";

			if(currentdate.getTime() - event.getDate().getTime() >0)
				return "Event finished you can't participate ";
			
			for(int i=0 ; i<participations.size();i++) {
				if(participations.get(i).getEvent().getIdEvent() == idevent && 
						participations.get(i).getUser().getIduser() == iduser)
			
					return "You are already participate !!";
				
			}
			
			if(event.getNumberOfPlaces() > 0) {
				
				System.out.println("/////---"+participations.size()+", "+event.getNbrTicketEarlyParticipants());
				if(event.isEarlyParticipants() == true && participations.size()<event.getNbrTicketEarlyParticipants()) {
		
					float discPercent =(100f-event.getDiscountPercentage())/100f; 
					float newPrice =event.getPriceTicket() * discPercent;
				
				participationPK.setIdEvent(event.getIdEvent());
				participationPK.setIdUser(iduser);
				p.setParticipationDate(date);	
				event.setNumberOfPlaces(event.getNumberOfPlaces() - 1);
			    event.setParticipantsNbr(event.getParticipantsNbr() + 1);
			    System.out.println("coll amount =="+event.getCollAmount()+", newPrice="+newPrice);
				event.setCollAmount(event.getCollAmount()+ newPrice);
				user.setAccBalance(user.getAccBalance()-newPrice);
				p.setPrice(newPrice);
				event.getJackpot().setTotal(event.getJackpot().getTotal()+newPrice);

				p.setParticipationPK(participationPK);
				//participationPK.setNumber(number);
				p.setParticipationDate(new Date());
				JSONArray productJsonArray = new JSONArray();
	    		try {
		    		JSONObject productJson = new JSONObject("{}");

				productJson.put("Participation"," added successfully");
				productJson.put("Participation name event",event.getTitle());
				productJson.put("Participation name user",user.getFirstname());
				productJson.put("event price ticket before participation",event.getPriceTicket());
				productJson.put("event price ticket after participation with reduction",newPrice);

				productJson.put("Participation"," added successfully");

				productJson.put("Participation id event",p.getEvent());

				productJson.put("Added by","User");
				
				 productJsonArray.put(productJson);
					iParticipationRepository.save(p);
					iUserRepository.save(user);


				 return productJsonArray.toString();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				}	
				else {
					
					participationPK.setIdEvent(event.getIdEvent());
					participationPK.setIdUser(iduser);
					p.setParticipationDate(date);	
					event.setNumberOfPlaces(event.getNumberOfPlaces() - 1);
				    event.setParticipantsNbr(event.getParticipantsNbr() + 1);
				    System.out.println("coll amount =="+event.getCollAmount()+", newPrice="+event.getPriceTicket());
					event.setCollAmount(event.getCollAmount()+ event.getPriceTicket());
					user.setAccBalance(user.getAccBalance()-event.getPriceTicket());
		
					event.getJackpot().setTotal(event.getJackpot().getTotal()+ event.getCollAmount());
		p.setPrice(event.getPriceTicket());
					p.setParticipationPK(participationPK);
					//participationPK.setNumber(number);
					p.setParticipationDate(new Date());
					
					
					iParticipationRepository.save(p);

				iUserRepository.save(user);
			}
			return "Affected successfully with discount percentage";
			
		}

	 
		
		
		
		return "Event places is full";
	}


	@Override
	public List<Participation> participationsList() {
		List<Participation> list = (List<Participation>) iParticipationRepository.findAll();
		return list;
}


	@Override
	public List<Participation> myParticipations() {
		// TODO Auto-generated method stub
		return null;
	}

}
