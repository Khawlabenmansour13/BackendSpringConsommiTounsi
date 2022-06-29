package tn.esprit.pidev.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.pidev.entities.Donation;
import tn.esprit.pidev.entities.Event;
import tn.esprit.pidev.entities.Jackpot;

@Repository
public interface IJackpotRepository extends CrudRepository<Jackpot, Integer> {
	/**********************************Find jackpot ByEvent***************************/
	@Query("SELECT e.jackpot FROM Event e WHERE e.id=:id ")
	Jackpot findJackpotEvent(@Param("id") int event);
	

}
