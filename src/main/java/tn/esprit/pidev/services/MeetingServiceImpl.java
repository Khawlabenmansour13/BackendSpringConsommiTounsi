package tn.esprit.pidev.services;
import java.nio.file.AccessDeniedException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.entities.AppointmentStatus;
import tn.esprit.pidev.entities.Event;
import tn.esprit.pidev.entities.Meeting;
import tn.esprit.pidev.entities.Roletype;
import tn.esprit.pidev.entities.Sponsor;
import tn.esprit.pidev.entities.User;
import tn.esprit.pidev.repositories.IEventRepository;
import tn.esprit.pidev.repositories.IMeetingRepository;
import tn.esprit.pidev.repositories.IUserRepository;

@Service
public class MeetingServiceImpl  implements IMeetingService{
    @Autowired
	private IMeetingRepository iMeetingRepository;
	@Autowired
	private IEventRepository iEventRepository;
	@Autowired
	private IUserRepository iUserRepository;
    private final int NUMBER_OF_CANCELATION_PER_MONTH = 3;
	
	
	@Override
	public ResponseEntity<?> createNewMeeting(int sponsorId, int eventId, Meeting meeting) {
        List<Meeting> results = new ArrayList<>();
		results = (List<Meeting>) iMeetingRepository.findAll();
		    if(meeting.getStartDate().isBefore(LocalDate.now()))
			return ResponseEntity.ok("meeting could not added before today : "+meeting.getStartDate());
			User sponsor = iUserRepository.findSponsorById(sponsorId);
			//findEventByManagerEvent 
			Event event = iEventRepository.findById(eventId).get();
			//User managerEvent = iUserRepository.findManagerEvent(Roletype.MANAGEREVENT,event.getManagerEvent().getIduser());
			for(int i = 0 ; i< results.size() ; i++ ) {
				System.out.println("Meet :"+meeting);
				if(results.get(i).getStartDate().isEqual(meeting.getStartDate()))
					return ResponseEntity.ok("meet exist please create another one ");
			}
			
			if(sponsor.getRole().getType().equals(Roletype.SPONSOR)) {

	 			meeting.setStatus(AppointmentStatus.SCHEDULED);
				meeting.setCreatedAt(LocalDateTime.now());
				meeting.setUsers(sponsor);
				meeting.setEvents(event);
				System.out.println("Meet betwwen Sponsor ="+meeting.getUsers().getFirstname() + "and managerEvent "+event.getManagerEvent().getFirstname());
				iMeetingRepository.save(meeting);
				System.out.println("EEEE SPONSOR "+ sponsor);
				System.out.println("EEEE Event Manager "+ event.getManagerEvent().getFirstname());
				return ResponseEntity.ok(" Meet created succesfully start at:"+meeting.getStartDate()+", and finished at: "+meeting.getTime()+"\n sponsor affected is "
				+meeting.getUsers().getFirstname()+" and boutique ManagerEvent is "+event.getManagerEvent().getFirstname());
				
			}
			return ResponseEntity.ok("Opps create New Meeting failed");

	}

	@Override
	public int deleteMeeting(int id) {
		
		Meeting m  =  iMeetingRepository.findById(id).get();
		
		if(m.getStatus() == AppointmentStatus.FINISHED) {
			iMeetingRepository.deleteById(id);
			return 1;
		}
		return 0;
		 
	
	}

	@Override
	public int updateMeeting(Meeting m , int id) {
		System.out.println("m = "+m);
		 return iMeetingRepository.updateMeeting(m.getStatus(), m.getTypeMeeting(), m.getDescription(),m.getStartDate(),m.getTime(), id);
	}


	@Override
	public List<Meeting> getAllMeeting() {
		
		List<Meeting>meetings = (List<Meeting>) iMeetingRepository.findAll();
		List<Meeting> result =  new ArrayList<Meeting>();
		meetings.forEach(m ->result.add(m));
		return result;
	}

	@Override
	public Meeting getMeetingById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Meeting> getMeetingByAdminId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

	@Override
	public List<Meeting> getConfirmedMeetingBSponsorId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Meeting> getCanceledMeetingsBySponsorIdForCurrentMonth(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCancelNotAllowedReason(int userId, int meetingId) {
		User user = iUserRepository.findById(userId).get();
		Meeting meeting = iMeetingRepository.findById(meetingId).get();
	    LocalDateTime localDateTime1 = meeting.getStartDate().atStartOfDay();
	    
	    if(meeting.getEvents().getManagerEvent().equals(user)) {
	    	if(!meeting.getStatus().equals(AppointmentStatus.SCHEDULED)) {
				return "cannot cancel meeting SCHEDULED";

			
		}
		
		
			else if(LocalDateTime.now().plusDays(1).isAfter(localDateTime1)) {
			System.out.println("===>"+localDateTime1.plusDays(1));
			 JSONArray productJsonArray = new JSONArray();
	    		try {
		    		JSONObject productJson = new JSONObject("{}");

				productJson.put("Response","meeting which will be in less than 24 hours cannot be canceled");

		     	 productJsonArray.put(productJson);
		     	 return productJsonArray.toString();


	    		}catch(Exception e) {
	    			e.printStackTrace();
	    		}
		     	 
		}
		
		else if(iMeetingRepository.findManagerEventIdCanceledAfterDate(userId,LocalDateTime.now()
				.with(TemporalAdjusters.firstDayOfMonth())).size() >= NUMBER_OF_CANCELATION_PER_MONTH){
			return "you exceeded maximum number of cancelations per month";
		}
		else {
			return null;
		}
		
	}
	    //SI SPONSOR
	    
		if(meeting.getUsers().equals(user)) {
			if(!meeting.getStatus().equals(AppointmentStatus.SCHEDULED)) {
					return "cannot cancel meeting other than SCHEDULED";

				
			}
			
			
			else if(LocalDateTime.now().plusDays(1).isAfter(localDateTime1)) {
				System.out.println("===>"+localDateTime1.plusDays(1));

				 JSONArray productJsonArray = new JSONArray();
		    		try {
			    		JSONObject productJson = new JSONObject("{}");

					productJson.put("Response","meeting which will be in less than 24 hours cannot be canceled");

			     	 productJsonArray.put(productJson);
			     	 return productJsonArray.toString();


		    		}catch(Exception e) {
		    			e.printStackTrace();
		    		}
			     	 
			}
			
			else if(iMeetingRepository.findSponsorIdCanceledAfterDate(userId,LocalDateTime.now()
					.with(TemporalAdjusters.firstDayOfMonth())).size() >= NUMBER_OF_CANCELATION_PER_MONTH){
				return "you exceeded maximum number of cancelations per month";
			}
			else {
				return null;
			}
			
		}
		
		return null;
	}

	@Override
	public ResponseEntity<?>  cancelUserAppointmentById(int meetingId , int userId) {
		
			Meeting meeting = iMeetingRepository.findById(meetingId).get();
				
	        if (meeting.getEvents().getManagerEvent().getIduser() == userId || meeting.getUsers().getIduser() == userId) {
	        	meeting.setStatus(AppointmentStatus.CANCELED);
	           // User canceler = iUserRepository.findById(userId).get();
	            User canceler = iUserRepository.findById(userId).get();
	        	meeting.setCanceler(canceler);
	            meeting.setCanceledAt(LocalDateTime.now());
	            iMeetingRepository.save(meeting);
	            if (canceler.equals(meeting.getUsers())) {
	                //notificationService.newAppointmentCanceledByCustomerNotification(appointment, true);
	            	return ResponseEntity.ok("canceld by Sponsor : "+meeting.getUsers().getFirstname());
	            } else if (canceler.equals(meeting.getEvents().getManagerEvent())) {
	               // notificationService.newAppointmentCanceledByProviderNotification(appointment, true);
	            	return ResponseEntity.ok("canceld by Event Manager :"+meeting.getEvents().getManagerEvent().getFirstname());

	            }
	        } else {
	        	return ResponseEntity.ok("Unauthorized");
	        }
				

	        return  ResponseEntity.ok("not allowed to cancel this meet");
	        }

	@Override
	public List<Meeting> getMeetingBySponsorId(int spnosorId) {

		List<Meeting>meeting = (List<Meeting>) iMeetingRepository.findAll();
		List<Meeting>result = new ArrayList<>();
		User sponsor = iMeetingRepository.findUserMeeting(spnosorId);
		System.out.println("SPONSOR =" +spnosorId);
		for(Meeting m : meeting) {
			if( m.getUsers().getIduser() == sponsor.getIduser()
					) { 
				
				result.add(m);
			}
		}
		
		return result;
	}
	
	
	@Override
	public List<Meeting> getMeetingBySponsorAtDay(int SponsorId, String day) {
		List<Meeting>meeting = (List<Meeting>) iMeetingRepository.findAll();
		List<Meeting>result = new ArrayList<>();
		User user = iMeetingRepository.findSponsorMeeting(SponsorId);
		System.out.println("spoonsor =" +user);
		for(Meeting m : meeting) {
			if( m.getStartDate().isEqual(LocalDate.parse(day))) { 
				
				result.add(m);
			}
		}
		
		return result;
		}

	@Override
	public List<Meeting> getMeetingByActorEventId(int actorEvent) {
		List<Meeting>meeting = (List<Meeting>) iMeetingRepository.findAll();
		List<Meeting>result = new ArrayList<>();
		Event event = iMeetingRepository.findEventMeeting(actorEvent);
		System.out.println("ActorEvent =" +event.getManagerEvent());
		for(Meeting m : meeting) {
			if( m.getEvents() == event) { 
				
				result.add(m);
			}
		}
		
		return result;
	}

	@Override
	public List<Meeting> getMeetingByActorEventAtDay(int actorEvent, String day) {
		Event event = iMeetingRepository.findEventMeeting(actorEvent);
		System.out.println("ManagerEvent= "+day);
		LocalDate l = LocalDate.parse(day);
        return iMeetingRepository.getMeetingBySponsorAtDay(event.getIdEvent(), l, l.plusDays(1));
	}

	@Override
	public int countMeetingByMonth() {
		return  iMeetingRepository.countMeetingByMonth();
	}

	@Override
	public int countMeetingByYear() {
		// TODO Auto-generated method stub
		return iMeetingRepository.countMeetingByYear();
	}

	@Override
	public int countMeetingByDay() {
		// TODO Auto-generated method stub
		return  iMeetingRepository.countMeetingByDay();
	}
	

	
	
}
