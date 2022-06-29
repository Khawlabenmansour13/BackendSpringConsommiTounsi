package tn.esprit.pidev.services;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tn.esprit.pidev.entities.Advertisement;
import tn.esprit.pidev.entities.CategoryEvent;
import tn.esprit.pidev.entities.Donation;
import tn.esprit.pidev.entities.Event;
import tn.esprit.pidev.entities.Jackpot;
import tn.esprit.pidev.entities.Notification;
import tn.esprit.pidev.entities.Participation;
import tn.esprit.pidev.entities.ParticipationPK;
import tn.esprit.pidev.entities.Roletype;
import tn.esprit.pidev.entities.User;
import tn.esprit.pidev.repositories.IAdvertisementRepository;
import tn.esprit.pidev.repositories.IDonationRepository;
import tn.esprit.pidev.repositories.IEventRepository;
import tn.esprit.pidev.repositories.IJackpotRepository;
import tn.esprit.pidev.repositories.INotificationRepository;
import tn.esprit.pidev.repositories.IParticipationRepository;
import tn.esprit.pidev.repositories.IUserRepository;
@Service
public class EventServiceImpl implements IEventService {
	@Autowired 
	private IEventRepository iEventRepository;
	@Autowired 
	private IAdvertisementRepository iAdvertisementRepository;
	@Autowired 
	private IParticipationRepository iParticipationRepository;
	@Autowired
	private IJackpotRepository iJackpotRepository;
	@Autowired 
	private IUserRepository iUserRepository;
	@Autowired
	private INotificationRepository iNotificationRepository;
	
	@Autowired 
	private IDonationRepository iDonationRepository;
	int number = 1;
	
	
    private static final long interval_milliSeconds = 60*60*1000; // scheduled to run once every hour

    /**********************Creating add method that insert event into database***********************/
	@Override
	public Event addEvent(Event e ,int iduser) {
		// by id
		User user = iUserRepository.findById(iduser).get();
		//user connected
		//
	
		 if(user.getRole().getType() == Roletype.MANAGEREVENT) {
			Jackpot j = new Jackpot();
			j.setTotal(0);
			e.setJackpot(j);
			e.setManagerEvent(user);
			Notification n = new Notification();
			n.setUser(null);
			n.setDate(new Date());
			n.setTarget("notify all users");
			n.setDescription("event added welcome and never forget to join us");
			n.setStatus("Not seen yet");
			n.setTime(new Date());
			n.setEvent(e);
			n.setSubject("Event added");
			iJackpotRepository.save(j);
	        iEventRepository.save(e);
			iNotificationRepository.save(n);
			return e;
		}
		return null;
	
}
	/*******************Creating deleting method that remove event by id  from database***************/
    @Override
	public void deleteEvent(int id) {
		Event e = iEventRepository.findById(id).get();
		iEventRepository.delete(e);
	}
	/*******************Creating update method that upgrade event from database**********************/
	@Override
	public int updateEvent(Event e, int id) {
        //DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
     
		return iEventRepository.updateEvent(e.getTitle(),e.getDate() , e.getHour(), 
					e.getAddress(), e.getDescription(), e.getNumberOfPlaces(),e.getPriceTicket(),e.getIsStatus(),e.getImage(),e.getIdEvent());
	}
	/**********************Creating getAll method that retrieve all event from database **************/
	@Override
	public List<Event> getAllEvents() {
		List<Event>events = new ArrayList<Event>();
		iEventRepository.findAll().forEach(e ->events.add(e));
		return events;
	}
	/******************Creating getByid method that retrieve event detail from database****************/
	@Override
	public Event getEventById(int id) {
		Event event  = iEventRepository.findById(id).get();
		
		return event;
	}
	/**************Creating getByName method that retrieve event detail from database******************/
	@Override
	public Event findEventByName(String name) {
		return iEventRepository.findEventByName(name);
	}
	/**************Creating getByCategoryEvent method that retrieve event detail from database************/
	@Override
	public List<Event> filterEvent(CategoryEvent category) {
		return iEventRepository.filterByCategory(category);
	}
	
	/********************Creating affectedEventUser method from database************************************/
	/*@Override
	public String affecterEventUser(int iduser, int idevent) {
        Event event = iEventRepository.findById(idevent).get();
		int userId = iduser;//user id;
        Participation p = new Participation(); 
		ParticipationPK participationPK = new ParticipationPK(); 
		List<Participation> listParticipations = new ArrayList<Participation>(); 
		listParticipations = (List<Participation>)iParticipationRepository.findAll();
		for(int i = 0; i< listParticipations.size();i++) {
			
			if(listParticipations.get(i).getEvent().getIdEvent() == idevent && 
					listParticipations.get(i).getUser().getIduser() == iduser)
		
				return "You are already participate !!";
			
			else if(listParticipations.get(i).getEvent().getIdEvent()== idevent) { 
				number++;
			}
			else if(listParticipations.get(i).getUser().getIduser() == iduser) {
				number =1;
			}
			
		}
		participationPK.setIdEvent(event.getIdEvent());
		participationPK.setIdUser(userId); 
		participationPK.setNumber(number);
		p.setParticipationPK(participationPK);
        p.setParticipationDate(new Date());
		iParticipationRepository.save(p);
		return "Affected successfully!!";
		
	}
	*/
	/**************Creating affectedCategoryEvent method from database************************************/
	@Override
	public String affecterCategoryEvent(String category, int idevent,int iduser) {
		String msg=" ";

		User user = iUserRepository.findById(iduser).get();
			if(user.getRole().getType() == Roletype.MANAGEREVENT) {

				Event event = iEventRepository.findById(idevent).get();
				try {
				for(CategoryEvent c : CategoryEvent.values()) {
					if(c == CategoryEvent.valueOf(category)) {
						event.setCategoryEvent(CategoryEvent.valueOf(category));
						iEventRepository.save(event);
						return msg ="Category Affected successfully!";
					}
				}
			}catch(Exception e) {
					 msg="Failed to affected Category";
				}
				return msg;

			}
			else 
				return "Only MANGERVENT Could affect category to event ";
		

	}
    /****************************Display  Top 3 Events Number of Views**************************************************/
	@Override 
	public Map<Integer, Integer> getEventsByViews() {
		List<Integer>listId = new ArrayList<>();
		List<Integer>listViews = new ArrayList<>();
		Map<Integer,Integer> h = new HashMap<>();
        for(Event e : iEventRepository.findAll()) {
			listId.add(e.getIdEvent());
			listViews.add(e.getViews());
		}
		List<Integer>sortedList = new ArrayList<>(listViews);
		Collections.sort(sortedList);
		for(int i = 0 ; i <3 ; i++) {
			int max = sortedList.get(sortedList.size()-1);
			int ind = listId.get(listViews.indexOf(max));
			h.put(ind, max);
            System.out.println(ind +" "+ max);
			sortedList.remove(sortedList.size()-1);
			listViews.set(listViews.indexOf(max), -1); 
		}
		return h;
	}
	
	/**********************Display top 3 Events by numberViews ********************************************************/
	//creating displayBestEventsByViews that display most 3 views event
		@Override
		public List<String> displayBestEventsByViews() {
			
			List<String>list = new ArrayList<>();
			String s ="";
			List<Integer>listId = new ArrayList<>();
			List<Integer>listViews = new ArrayList<>();
			
			List<Event> listEvent = (List<Event>)iEventRepository.findAll();
			
			for(Event e : listEvent) {
				listId.add(e.getIdEvent());
				listViews.add(e.getViews());
			
			}
			
			List<Integer> sortedList = new ArrayList<>(listViews);
			
			Collections.sort(sortedList);
			
			for(int i = 0 ; i<3 ; i++) {
				int max = sortedList.get(sortedList.size()-1);
				int ind = listId.get(listViews.indexOf(max));
				s = (i+1)+"--Event: "+iEventRepository.findById(ind).get().getTitle()+"  = with"+max+" views";
				list.add(s);
				sortedList.remove(sortedList.size()-1);
				listViews.set(listViews.indexOf(max), -1);
				}
			
			
			return list;
		}
		/*******************affected event  *************/		
	@Override
	public String affecterEventAdv(int idavert, int idevent) {
		Event event = iEventRepository.findById(idevent).get();
		Advertisement adv= iAdvertisementRepository.findById(idavert).get();
		adv.setEvent(event);
		iAdvertisementRepository.save(adv);
		return "Event affected succesfully to advertisement";
	
	}
/*******************List event  *************/
	@Override
	public List<Event> upcomingEvents() {
		List<Event> list= iEventRepository.upcomingEvents();
		return list;
	}
	
	

/*****************RefundUserEvent*************/
	@Override
	public String  refundUsers(int idevent) {
		JSONArray productJsonArray = new JSONArray();

		java.util.Date date = new java.util.Date();
		User u = new User();
		System.out.println(idevent);
		Event event = iEventRepository.findById(idevent).get();
		System.out.println("hiiii");
		double collectAmount = event.getCollAmount();
        List<Participation>listParticipations = iParticipationRepository.getParticipationByEvent(event);
		for(Participation p : listParticipations ) {
			 u = p.getUser();
			float refundPrice = p.getPrice();
			event.setCollAmount(event.getCollAmount() - refundPrice);
			u.setAccBalance(u.getAccBalance()+refundPrice);
			event.getJackpot().setTotal(event.getJackpot().getTotal()- refundPrice);
			iParticipationRepository.delete(p);
			iEventRepository.save(event);//mise a jour lil colonne amount 
			Notification n = new Notification();
			n.setEvent(event);
			n.setUser(u);
			
			n.setTarget("Sepecific users");
			LocalTime localTime = LocalTime.now();
            n.setTime(Time.valueOf(localTime) );
			n.setSubject("Canceled Event");
			n.setDescription("Dear "+u.getFirstname()+" "+u.getLastname()+""
					+ ",we regret to announce that the event "+event.getTitle()+" you want to attend has been canceled for some reason."
					+ " That's why, we have refunded your ticket price. If there is a problem, do not hesitate to contact us."
					+ " Thank you.");
			
			n.setDate(date);
			n.setStatus("Not seen yet");
			iNotificationRepository.save(n);
			
    		try {
	    		JSONObject productJson = new JSONObject("{}");

			productJson.put("Status ","Event canceld successfully with refund");
			productJson.put("Event id" , event.getIdEvent());
			productJson.put("Event Name" , event.getTitle());
			
			productJson.put("Money retreived",collectAmount);
			productJson.put("Notification send to user ",n.getDescription());
			productJson.put("Added by","Admin");
	     	 productJsonArray.put(productJson);

			 return productJsonArray.toString();

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}

		
		List<Donation>donationsResult =  iDonationRepository.DonationOfEvent(event);
		
		for(Donation d : donationsResult) {
			User user = d.getUser();
			
			float refundedAmount = d.getAmount();// Amount from  donation
			System.out.println("donation="+refundedAmount);
			System.out.println("jackpot before=="+event.getJackpot());
			event.getJackpot().setTotal(event.getJackpot().getTotal()- refundedAmount);
			System.out.println("jackpot after retrieve money=="+event.getJackpot().getTotal());
			System.out.println("collAmount before=="+event.getCollAmount());
			event.setCollAmount(event.getCollAmount()-refundedAmount);
			
			System.out.println("jackpot=="+event.getCollAmount());

			System.out.println("Accurance balance before="+u.getAccBalance());
			u.setAccBalance(u.getAccBalance()+refundedAmount);

			System.out.println("accuranceBalance="+u.getAccBalance());
			
			//************Notification*******************//
			Notification notification = new Notification();
			notification.setSubject("Remoboursement");
			notification.setUser(user);
			notification.setDescription("Dear "+user.getFirstname()+" We annonce that the event"+event.getTitle()+
					"was canceled for some reasons that's why we refunded your donation . for more informations do not hesitate to contact us"
					+ "thank you");
			notification.setDate(new Date());
			notification.setStatus("not seen Yet");
			
			iDonationRepository.deleteById(d.getIdDonation());
			iEventRepository.save(event);
			iUserRepository.save(u);
    		try {
	    		JSONObject productJson = new JSONObject("{}");

			productJson.put("update Donation", d.getIdDonation());
			productJson.put("Donation Event id" , d.getEvent().getIdEvent());
			productJson.put("Event Name" , event.getTitle());
			
			productJson.put("Money retreived",collectAmount);

			productJson.put("Added by","Admin");
	     	 productJsonArray.put(productJson);

			 productJsonArray.put(productJson)	;	
			 return productJsonArray.toString();
    		}catch(Exception e ) {
    			e.printStackTrace();
    		}
		}
		
		return "Refund fail";
		
	}
	/*
@Override
public String addParticipation(int iduser, int idevent) {
	
	Event event = iEventRepository.findById(idevent).get();
	User u = iUserRepository.findById(iduser).get();
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	int number = 0;
	Participation p = new Participation();
	ParticipationPK participationPK = new ParticipationPK();
	List<Participation>participations = (List<Participation>) iParticipationRepository.findAll();
	for(int i=0 ; i<participations.size();i++) {
		if(participations.get(i).getEvent().getIdEvent() == idevent && 
				participations.get(i).getUser().getIduser() == iduser)
	
			return "You are already participate !!";
		
	}
	
	if(event.getNumberOfPlaces() > 0) {
		float discPercent =(100f-event.getDiscountPercentage())/100f; 
		float newPrice =event.getPriceTicket() * discPercent;
	
	participationPK.setIdEvent(event.getIdEvent());
	participationPK.setIdUser(iduser);
	p.setParticipationDate(date);	
	event.setNumberOfPlaces(event.getNumberOfPlaces() - 1);
    event.setParticipantsNbr(event.getParticipantsNbr() + 1);
    System.out.println("coll amount =="+event.getCollAmount()+", newPrice="+newPrice);
	event.setCollAmount(event.getCollAmount()+ newPrice);
	p.setPrice(newPrice);
	p.setParticipationPK(participationPK);
	//participationPK.setNumber(number);
	p.setParticipationDate(new Date());
	
	
	iParticipationRepository.save(p);
	}
	return "Affected successfully with discount percentage";
	}

	*/
/*@Override
	public String affecterEventJackpot(int idjack, int idevent) {
		Event event = iEventRepository.findById(idevent).get();
		Jackpot jack= iJackpotRepository.findById( idjack).get();
		System.out.println("hii="+event+", "+jack);
	
		event.setJackpot(jack);
		iJackpotRepository.save(jack);
		return "Jackpot affected succesfully to event";
	
	}*/
	/*******************displayEventsByParticipants*************/
@Override
public List<String> displayEventsByParticipants() {
	// TODO Auto-generated method stub
	List<Event> events = (List<Event>) iEventRepository.findAll();
	List<Integer>listIdEvent = new ArrayList<>();
	List<Integer>listNbrParticipantEvent = new ArrayList<>();
	List<String>results = new ArrayList<>();
	String message = "";
	for(Event event : events) {
		listIdEvent.add(event.getIdEvent());
		listNbrParticipantEvent.add(event.getParticipantsNbr());
		
	}
	
	List<Integer>sortListed =new ArrayList<>(listNbrParticipantEvent);
	Collections.sort(sortListed);
	
	for(int i = 0; i<2 ; i++) {
		int max = sortListed.get(sortListed.size() - 1);//dernier val 
		int idEventByPart = listIdEvent.get(listNbrParticipantEvent.indexOf(max));//search event eli andou akther partic pour retourneer l'id de l'event 
		message ="Event "+iEventRepository.findById(idEventByPart).get().getTitle()+"with "+max+" Participations";
		results.add(message);
		sortListed.remove(sortListed.size()-1);
		listNbrParticipantEvent.set(listNbrParticipantEvent.indexOf(max),-1);
	}
	
	return results;
	
	
}
/*******************displayEventsByCollAmount*************/
@Override
public List<String> displayEventsByCollAmount() {
	// TODO Auto-generated method stub
	List<Event> events = (List<Event>) iEventRepository.findAll();
	List<Integer>listIdEvent = new ArrayList<>();
	List<Float>listCollAmountEvent = new ArrayList<>();
	List<String>results = new ArrayList<>();
	String message = "";
	for(Event event : events) {
		listIdEvent.add(event.getIdEvent());
		listCollAmountEvent.add(event.getCollAmount());
		
	}
	
	List<Float>sortListed =new ArrayList<>(listCollAmountEvent);
	Collections.sort(sortListed);
	
	for(int i = 0; i<2 ; i++) {
		float max = sortListed.get(sortListed.size() - 1);//ekher valeur 
		int idEventByPart = listIdEvent.get(listCollAmountEvent.indexOf(max));//nlawj al event eli andou akther partic o yraje3 id event 
		message ="Event "+iEventRepository.findById(idEventByPart).get().getTitle()+"with "+max+" dinars  , Collectd Amount ";
		results.add(message);
		sortListed.remove(sortListed.size()-1);
		listCollAmountEvent.set(listCollAmountEvent.indexOf(max),(float) -1);
	}
	
	return results;
	
	
}
/*******************Search event by id*************/
	@Override
	public Event findbyId(int id) {
		return iEventRepository.findById(id).get();
	}
	
	
	//Resultat Events List between Two Dates 
	public String ResultEvent(List<Event> events,int i) {
		return "Event "+i+""+"Titre: "+events.get(i).getTitle()+
				""+"--Description: "+events.get(i).getDescription()+
				""+"--Addresse: "+events.get(i).getAddress()+
				""+"--CollectAmount: "+events.get(i).getCollAmount()+
				""+"--Discount: "+events.get(i).getDiscountPercentage()+
				""+"--PriceTicket: "+events.get(i).getPriceTicket()+
				""+"--Views: "+events.get(i).getViews()+
				""+"--NumberOfPlaces: "+events.get(i).getNumberOfPlaces()+
				""+"--ParticipantsNbr: "+events.get(i).getParticipantsNbr()+
				""+"--Category: "+events.get(i).getCategoryEvent();
	}
	/*******************getEventTwoDatesBeetweentwoDate*************/
	@Override
	public List<String> getEventTwoDatesBeetween(Date date1, Date date2) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		List<Event>events = (List<Event>) iEventRepository.findAll();
		List<String>results = new ArrayList<>();
		
		if(date1.after(date2)) {
			return null;
		}
		
		for(int i = 0 ; i<events.size();i++) {
			if((events.get(i).getDate().after(date1) &&( events.get(i).getDate().before(date2)))) {
				results.add(ResultEvent(events,i));
			}
			
		}
		if(results.isEmpty()) {
			 results.add("Event Not Found between two dates we are sorry :( :( ");
			 return results;
		}
		return results; 
	}
	/****************************reintializeJackPotAfterDateEvent*********************/
	@Scheduled(fixedRate=interval_milliSeconds)
	@Override
	public void reintializeJackPotAfterDateEvent(int idevent) {
		Jackpot jack = iJackpotRepository.findJackpotEvent(idevent);
		Event event = iEventRepository.findById(idevent).get();
		Date now = new Date();
		if(now.getTime() - event.getDate().getTime() >=1) {
			jack.setTotal(0);
			iJackpotRepository.save(jack);
		}
		
		
		
	}
	/**************************passedEvents***********************/
	@Override
	public List<Event> passedEvents() {
		// TODO Auto-generated method stub
		List<Event> list= iEventRepository.passedEvents();
		List<Event>result = new ArrayList<>();
		list.forEach(p -> result.add(p));
		
		return result;	}
	
	
	
	@Override
	public ResponseEntity downloadFileFromLocal(String fileName) throws MalformedURLException, IOException {
		

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/")
				.path(fileName).toUriString();
		 StringBuilder result = new StringBuilder();
	        String line;

	        // add user agent 
	        URLConnection urlConnection = new URL(fileDownloadUri).openConnection();
	        urlConnection.setReadTimeout(5000);
	        urlConnection.setConnectTimeout(5000);

	        try (InputStream is = new URL(fileDownloadUri).openStream();
	             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

	            while ((line = br.readLine()) != null) {
	                result.append(line);
	            }

	        }

	        return ResponseEntity.ok(result);

	    }
	
	
}
