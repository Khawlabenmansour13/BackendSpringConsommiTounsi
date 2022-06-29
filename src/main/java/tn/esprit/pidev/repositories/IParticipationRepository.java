package tn.esprit.pidev.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.pidev.entities.Event;
import tn.esprit.pidev.entities.Participation;
@Repository
public interface IParticipationRepository extends CrudRepository<Participation, Integer> {
	
	/**********************************Find participation ByEvent***************************/
	@Query("SELECT p FROM Participation p WHERE p.event=:event")
	List<Participation>getParticipationByEvent(@Param("event")Event event);
	
	

}
