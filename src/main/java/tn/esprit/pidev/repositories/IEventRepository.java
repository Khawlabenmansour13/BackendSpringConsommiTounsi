package tn.esprit.pidev.repositories;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.entities.CategoryEvent;
import tn.esprit.pidev.entities.Event;
import tn.esprit.pidev.entities.Jackpot;
@Repository
public interface IEventRepository extends CrudRepository<Event,Integer> {

	/***************************EventByName**************************************/
	 
	@Query("SELECT event FROM Event event WHERE event.title = :title")
	public Event findEventByName(@Param("title")String title);
	
	/****************************EventByCategory***********************************/
	@Query("SELECT event FROM Event event WHERE event.categoryEvent =:categoryEvent")
	public List<Event> filterByCategory(@Param("categoryEvent")CategoryEvent eventCategory);
	
	
	/*******************************UpdateEvent*******************************************/
	@Modifying
	@Transactional
	@Query("UPDATE Event e SET e.title = :title  , e.date = :date , e.hour = :hour , e.address =:address , e.description = :description "
			+ ", e.numberOfPlaces = :numberOfPlaces , e.priceTicket = :priceTicket , e.status =:status, "
			+ " e.image = :image WHERE e.id = :id")
	
	public int updateEvent(@Param("title")String title,@Param("date")Date date,@Param("hour")Date hour,@Param("address")String address,
			@Param("description")String description,@Param("numberOfPlaces")int numberOfPlaces,
			@Param("priceTicket")float priceTicket,@Param("status")boolean status,@Param("image")byte[] image,@Param("id")int id	);

	/************************************Event current date*************************************/
	@Query("SELECT ev FROM Event ev WHERE ev.date >= CURRENT_DATE() ")
	public List<Event> upcomingEvents();
	

	@Query("SELECT e.jackpot FROM Event e WHERE e.jackpot =:jackpot")
	public Jackpot findJackpot(@Param("jackpot")Jackpot jackpot);
	
	/***************************passedEvents*********************
	 * 
	 * @param 
	 * @return List<Events>
	 * 
	 */
	
	@Query("SELECT ev FROM Event ev WHERE ev.date <= current_timestamp()")
	public List<Event> passedEvents();
	
}


