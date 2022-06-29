package tn.esprit.pidev.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tn.esprit.pidev.entities.Event;
import tn.esprit.pidev.entities.Sponsor;
import tn.esprit.pidev.entities.Sponsoring;

public interface ISponsoringRepository extends CrudRepository<Sponsoring, Integer> {
	
	@Query("SELECT s FROM Sponsoring s WHERE s.event=:event")
	List<Sponsoring>getSponsoringByEvent(@Param("event")Event event);

	

	@Query("SELECT COUNT(sp.sponsor.iduser) FROM Sponsoring sp WHERE sp.sponsor.iduser =:iduser AND sp.event.idEvent =:idevent")
	int countNumberOfRequest(@Param("iduser")int iduser,@Param("idevent")int idevent);

	
	@Query("SELECT s FROM Sponsoring s WHERE s.event=:event and s.sponsor =:sponsor")

	Sponsoring findSponsoring(@Param("event")Event event,@Param("sponsor")Sponsor sponsor);
}
