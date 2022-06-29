package tn.esprit.pidev.controllers;
import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pidev.entities.Meeting;
import tn.esprit.pidev.services.IMeetingService;

@RestController
public class MeetingController {
	@Autowired
	IMeetingService iMeetingService;
     
	//creating post mapping that post the advertisement detail in the database  
	@PostMapping("/meetings/create-meeting/{idsponsor}/{idevent}")  
	@ResponseBody
	private ResponseEntity<?> addMeeting(@RequestBody Meeting m ,
			@PathVariable("idsponsor")int idsponsor, @PathVariable("idevent")int idevent) throws ParseException   
	{ 
		 return iMeetingService.createNewMeeting(idsponsor,idevent, m);
	}
	
	
	//creating a delete mapping that deletes a specified advertisement 
	
	
			@GetMapping("/meetings/cancel-not-allowed-meeting/{idMeeting}/{idsponsor}")  
			private ResponseEntity<?> canceledNotAllowedMeeting(@PathVariable("idMeeting") int idmeeting,@PathVariable("idsponsor") int idsponsor)   
			{  
				
				String notAllowedCanceled = iMeetingService.getCancelNotAllowedReason(idsponsor,idmeeting);
				
				return ResponseEntity.ok("NOT ALLOWED :"+notAllowedCanceled);
			}
			
			
			@DeleteMapping("/meetings/deleteMeeting/{idMeeting}")  
			private ResponseEntity<?> deleteMeeting(@PathVariable("idMeeting") int idmeeting)   
			{  
				
				
				if(iMeetingService.deleteMeeting(idmeeting)==1)
				
				return ResponseEntity.ok("deleted successfully");
				else 
					return ResponseEntity.ok("failed");

					
				
			}
			
			@PutMapping("/meetings/cancel-meeting/{idMeeting}/{iduser}")  
			private ResponseEntity<?> canceledMeeting(@PathVariable("idMeeting") int idmeeting,@PathVariable("iduser") int userId)   
			{  
								
				return ResponseEntity.ok(iMeetingService.cancelUserAppointmentById(idmeeting, userId));
			}
			
			//creating put mapping that updates the advertisement detail   
			@PutMapping("/meetings/update-meeting/{idm}")  
			private int updateMeeting(@RequestBody Meeting adv, @PathVariable("idm")int idm)   
			{  
				return iMeetingService.updateMeeting(adv, idm); 
			}
	//creating a get mapping that retrieves all the advertisement detail from the database   
			@GetMapping("/meetings/get-all-meeting")  
			private List<Meeting> getAllMet()   
			{  
				return iMeetingService.getAllMeeting(); 
			}
	//creating a get mapping that retrieves the detail of a specific advertisement 
			@GetMapping("/meeting/detail-meeting/{idm}")  
			private Meeting getMeeting(@PathVariable("idm") int idAd)   
			{  
				return iMeetingService.getMeetingById(idAd);
			} 
	
			//creating a get mapping that retrieves the detail of a specific advertisement 
			@GetMapping("/meeting/getMeetingBySponsorId/{ids}")  
			private List<Meeting> getMeetingBySponsorId(@PathVariable("ids") int ids)   
			{  
				return iMeetingService.getMeetingBySponsorId(ids);
			} 
			
			@GetMapping("/meeting/getMeetingByManagerEventAtDay/{ids}/{date}")  
			private List<Meeting> getMeetingByManagerEventAtDay(@PathVariable("ids") int ids, @PathVariable
					("date")String localDate)   
			{  
				return iMeetingService.getMeetingByActorEventAtDay(ids,localDate);
			} 
			@GetMapping("/meeting/getMeetingByActorEventId/{ids}")  
			private List<Meeting>getMeetingByActorEventId (@PathVariable("ids") int ids)   
			{  
				return iMeetingService.getMeetingByActorEventId(ids);
			} 
			
			@GetMapping("/meeting/getMeetingBySponsorAtDay/{ids}/{date}")  
			private List<Meeting> getMeetingBySponsorAtDay(@PathVariable("ids") int ids, @PathVariable
					("date")String localDate)   
			{  
				return iMeetingService.getMeetingBySponsorAtDay(ids,localDate);
			} 
			
			@GetMapping("/meetings/countMeetingByCurrentMonth")  
			private int countMeetingByCurrentMonth()   
			{  
				return iMeetingService.countMeetingByMonth();
			} 
			
			@GetMapping("/meetings/countMeetingByCurrentYear")  
			private int countMeetingByCurrentYear()   
			{  
				return iMeetingService.countMeetingByYear();
			} 
			

			@GetMapping("/meetings/countMeetingByCurrentDay")  
			private int countMeetingByCurrentDay()   
			{  
				return iMeetingService.countMeetingByDay();
			}
			
	
	

}