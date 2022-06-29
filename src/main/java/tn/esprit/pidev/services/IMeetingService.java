package tn.esprit.pidev.services;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;



import tn.esprit.pidev.entities.Meeting;

public interface IMeetingService {
	
	
    //void createNewAppointment(int parentId, int kindergardenId, int customerId, LocalDateTime start);
    public int deleteMeeting(int id);
	
    public int updateMeeting( Meeting m ,int id);
	
    public List<Meeting> getAllMeeting();
	
    public Meeting getMeetingById(int id);
    
    public List<Meeting> getMeetingByAdminId(int userId);


    public List<Meeting> getMeetingBySponsorId(int spnosorId);


    public List<Meeting> getMeetingBySponsorAtDay(int adminId, String day);
    
   public  List<Meeting> getMeetingByActorEventId(int actorEvent);


   public List<Meeting> getMeetingByActorEventAtDay(int actorEvent, String day);
    

    public List<Meeting> getConfirmedMeetingBSponsorId(int userId);

    public List<Meeting> getCanceledMeetingsBySponsorIdForCurrentMonth(int userId);

    public String getCancelNotAllowedReason(int sponsorId,int meetingId);
    public ResponseEntity<?>  cancelUserAppointmentById(int appointmentId, int userId);

	public ResponseEntity<?> createNewMeeting(int sponsorId, int eventId, Meeting meeting);

	 public int countMeetingByMonth();
	 public int countMeetingByYear();

	 public int countMeetingByDay();

	
   
	
}
