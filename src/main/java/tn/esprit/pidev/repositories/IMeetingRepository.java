package tn.esprit.pidev.repositories;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.pidev.entities.AppointmentStatus;
import tn.esprit.pidev.entities.Event;
import tn.esprit.pidev.entities.Meeting;
import tn.esprit.pidev.entities.User;
public interface IMeetingRepository extends CrudRepository<Meeting, Integer> {
	
	
	@Modifying
	@Transactional
	@Query("UPDATE Meeting e SET e.status = :status ,"
			+ " e.typeMeeting =:typeMeeting ,"
			+ " e.description = :description ,"
			+ " e.time = :time ,"

			+" e.startDate = :startDate"
			+ " WHERE e.idMeeting = :id")
	
	public int updateMeeting(@Param("status")AppointmentStatus status,@Param("typeMeeting")String
			typeMeeting,@Param("description")String description,@Param("startDate")LocalDate startDate,
			
			@Param("time")LocalTime time,
			@Param("id")int id );
			
	
	
    @Query("SELECT m FROM Meeting m WHERE m.users.iduser =:userId and m.canceler.iduser =:userId and m.canceledAt >= :date ")
	List<Meeting>findSponsorIdCanceledAfterDate(@Param("userId")int userId,@Param("date")LocalDateTime date);
    
    
    @Query("SELECT m FROM Meeting m WHERE m.events.managerEvent.iduser =:userId and m.canceler.iduser =:userId and m.canceledAt >= :date ")
  	List<Meeting>findManagerEventIdCanceledAfterDate(@Param("userId")int userId,@Param("date")LocalDateTime date);
      
    @Query("SELECT m.users FROM   Meeting m  WHERE m.users.iduser =:userId")
   	User findUserMeeting(@Param("userId")int userId);


    @Query("SELECT m.events FROM   Meeting m  WHERE m.events.idEvent =:eventId")
   	Event findEventMeeting(@Param("eventId")int eventId);
    @Query("SELECT m.users FROM   Meeting m  WHERE m.users.iduser =:iduser")
   	User findSponsorMeeting(@Param("iduser")int iduser);
    
    @Query("select a from Meeting a where a.events.idEvent = :idEvent and  a.startDate >=:dayStart and  a.startDate <=:dayEnd")
	List<Meeting> getMeetingBySponsorAtDay(@Param("idEvent")int idEvent, @Param("dayStart")LocalDate atStartOfDay, 
			@Param("dayEnd")LocalDate plusDays);
    
	 
	 @Query("Select count(*) From Meeting m  where month(m.startDate) = month(current_date)")
	 int countMeetingByMonth();


	 @Query("Select count(*) From Meeting m  where day(m.startDate) = day(current_date)")
	 int countMeetingByDay();
	 @Query("Select count(*) From Meeting m  where year(m.startDate) = year(current_date)")
	 int countMeetingByYear();


} 
