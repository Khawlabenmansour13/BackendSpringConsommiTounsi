package tn.esprit.pidev.controllers;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import tn.esprit.pidev.config.FileUploadUtil;
import tn.esprit.pidev.entities.CategoryEvent;
import tn.esprit.pidev.entities.Event;
import tn.esprit.pidev.entities.Participation;
import tn.esprit.pidev.services.IEventService;
import tn.esprit.pidev.services.IParticipationService;

@RestController
public class EventController {
    @Autowired  
	private IEventService iEventService; 
    
    @Autowired
    private IParticipationService iParticipationService;
	ObjectMapper objectMapper = new ObjectMapper();

	//creating post mapping that post the event detail in the database  
		@PostMapping(value="/event/add-event",produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {
				MediaType.APPLICATION_JSON_VALUE,
				MediaType.MULTIPART_FORM_DATA_VALUE,
				
		})  
		private ResponseEntity<?> addEvent(@RequestParam("iduser")int userid,@RequestPart("evJson")String evJson,@RequestPart("image") MultipartFile file){  
			 Event ev  = new Event();
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			System.out.println("image name ="+fileName);
			try {
				ev= objectMapper.readValue(evJson, Event.class);
			    
			    System.out.println("url ="+ServletUriComponentsBuilder.fromCurrentContextPath());
				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/")
						.path(fileName).toUriString();
				System.out.println("file url =====>"+fileDownloadUri);
				 
				String fileNameRepo = StringUtils.cleanPath(file.getOriginalFilename());
				String uploadDir = "uploads/event_image";
				ev.setImage(fileNameRepo.getBytes());
				
	            Event event = iEventService.addEvent(ev,userid);  
	            
				FileUploadUtil.saveFile(uploadDir, fileNameRepo, file);
				if(ev.getIdEvent() !=0)
				return ResponseEntity.ok(event);


			} catch (IOException e) {
				e.printStackTrace();
			}
			return ResponseEntity.ok("only ManagerEvent could add event");
			
		}
	
	//creating a delete mapping that deletes a specified event  
		@DeleteMapping("/event/delete-event/{eventid}")  
		private void deleteEvent(@PathVariable("eventid") int eventid)   
		{  
			iEventService.deleteEvent(eventid);  
		}
		
	//creating put mapping that updates the event detail   
		@PutMapping("/event/update-event/{eventid}")  
		private Event updateEvent(@RequestBody Event events, @PathVariable("eventid")int eventId)   
		{  
			iEventService.updateEvent(events,eventId);  
			return events;  
		}
		
	//creating a get mapping that retrieves all the event detail from the database   
		@GetMapping("/event/get-all-events")  
		private List<Event> getAllEvents()   
		{  
			return iEventService.getAllEvents();  
		} 
		
	//creating a get mapping that retrieves the detail of a specific event  
		@GetMapping("/event/detail-event/{eventid}")  
		private Event getEvent(@PathVariable("eventid") int eventid)   
		{  
			return iEventService.getEventById(eventid);  
		} 
		
	//creating get mapping that getEventByName   
        @GetMapping("/retrieve-Event-ByName/{name}")
		public Event getEventByName(@PathVariable String name) {
			 Event event = iEventService.findEventByName(name);
			return event;
			}
        
     //creating get mapping that getEventByCategory
        @GetMapping("/retrieve-Event-ByCategory/{category}")
    	public List<Event> getEventByCategory(@PathVariable CategoryEvent category) {
    		List<Event> event = iEventService.filterEvent(category);
    		return event;
    		}
      
    	//creating put mapping that affected participant event 
		/*@PutMapping("/event/affecter-participant-event/{iduser}/{idevent}")  
		private String affecterEventUser(@PathVariable("iduser")int iduser,@PathVariable("idevent")int idevent)   
		{  
		   return iEventService.affecterEventUser(iduser, idevent);
			
		}
		*/
        
      //creating put mapping that updates the event detail   
      		@PutMapping("/event/affecter-participant-event/{iduser}/{idevent}")  
      		private  String participateToEvent(@PathVariable("iduser")int iduser,@PathVariable("idevent")int idevent)   
      		{  
      		
      			String result = iParticipationService.addParticipation(iduser, idevent);
      		    return result;
      			
      		}
		
		//creating put mapping that affectedCategoryEvent  
		@PutMapping("/event/affecter-category-event/{category}/{idevent}/{iduser}")  
				private String affecterCategoryEvent(@PathVariable("category")String category,@PathVariable("idevent")int idevent, @PathVariable("iduser")int iduser)   
		{  
				
					return iEventService.affecterCategoryEvent(category, idevent,iduser);
					
		}
		//display views of Top 3 events
		@GetMapping("/event/displaybestEventsByViews")
		public List<String>  displaybestEventsByViews(){
			return iEventService.displayBestEventsByViews();
			}
		
		@GetMapping("/event/bestEventsByViews")
		public Map<Integer, Integer> bestEventsByViews(){
			return iEventService.getEventsByViews();
			}
		//display top 3 events by views 
		
		//affected advertisement  
		@PutMapping("/event/affected-advertisement-event/{idevent}/{idavert}")
		@ResponseBody
			public String affectEventAdver(@PathVariable("idevent")int idevent, @PathVariable("idavert")int idavert) {
			iEventService.affecterEventAdv(idavert, idevent);
			return "Event affected to advertisement successfully!!";
		}
		//displayEventevents-after-today-date
		@GetMapping("/event/displayEventevents-after-today-date")
		public List<Event> upcomingEvents() {
			List<Event>upevents = iEventService.upcomingEvents();
			return upevents;
		}
		
		@GetMapping("/event/displayPassedEvent")
		public List<Event> passedEvents() {
			List<Event>upevents = iEventService.passedEvents();
			return upevents;
		}
		//affected Jackpot  
		//@PutMapping("/event/affected-jackpot-event/{idevent}/{idjack}")
		//@ResponseBody
			/*public String affectEventJackpot(@PathVariable("idevent")int idevent, @PathVariable("idjack")int idjack) {
			
			return iEventService.affecterEventJackpot(idjack, idevent);
		}*/
		
		@DeleteMapping("/event/delete-canceled-event/{idevent}")
		@ResponseBody
		public String deleteCanceledEvent(@PathVariable("idevent")int idevent) {
			String rest =iEventService.refundUsers(idevent);
			iEventService.deleteEvent(idevent);
			return rest;
		}
		
		@GetMapping("/event/displayBestEventsByParticipations")
		public List<String> displayBestEventsByParticipations() {
			return iEventService.displayEventsByParticipants();
			
		}
		
		@GetMapping("/event/displayEventsByCollAmount")
		public List<String> displayEventsByCollAmount() {
			return iEventService.displayEventsByCollAmount();
			
		}
		@GetMapping("/event/retrieve-all-Participations")
		public List<Participation> getParticipations(){
			return iParticipationService.participationsList();
		}
		
		@GetMapping("/event/getEventsBetweenTwoDates/{date1}/{date2}")
		public List<String> getEventBetweenTwoDates(@PathVariable("date1")String date1,@PathVariable("date2")String date2) throws ParseException{
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			Date date1Converted = dateFormat.parse(date1);
			Date date2Converted = dateFormat.parse(date2);
			return iEventService.getEventTwoDatesBeetween(date1Converted,date2Converted);
		}
		
		@GetMapping("/event/uploads/{fileName}")
		public ResponseEntity downloadFileFromLocal(@PathVariable String fileName) throws MalformedURLException, IOException {
			return iEventService.downloadFileFromLocal(fileName);//
		}
			
}
