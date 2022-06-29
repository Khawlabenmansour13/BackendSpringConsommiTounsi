package tn.esprit.pidev.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.pidev.entities.Donation;
import tn.esprit.pidev.entities.Event;

public interface IDonationRepository extends CrudRepository<Donation,Integer >{
	/**********************************Find donation ByEvent***************************/
	@Query("SELECT d FROM Donation d WHERE d.event=:event ")
	List<Donation> DonationOfEvent(@Param("event") Event event);
	
	
	

}
