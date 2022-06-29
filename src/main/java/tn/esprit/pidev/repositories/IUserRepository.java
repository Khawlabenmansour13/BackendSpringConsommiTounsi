package tn.esprit.pidev.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.pidev.entities.Event;
import tn.esprit.pidev.entities.Roletype;
import tn.esprit.pidev.entities.Sponsor;
import tn.esprit.pidev.entities.User;

public interface IUserRepository extends CrudRepository<User, Integer> {

	
	//@Query("SELECT s  FROM User s  JOIN Role r WHERE s.role.idRole  =:r.idRole and r.type = 'SPONSOR' and r.idRole =:idsponsor")
	//public User findSponsor(@Param("idsponsor")int idsponsor);
	
	
	
	@Query("Select u FROM User u where u.role.type =:role and u.role.idRole =:idsponsor")
	User findSponsor(@Param("role")Roletype role, @Param("idsponsor") int idsponsor );
	
	@Query("Select u FROM User u where u.iduser =:idsponsor")
	User findSponsorById(@Param("idsponsor") int idsponsor );
	
	@Query("Select u FROM User u where u.role.type =:role and u.role.idRole =:idmanager")
	User findManagerEvent(@Param("role")Roletype role, @Param("idmanager") int idmanager );
	
	@Query("Select e FROM Event e join User u  on e.idEvent =:idEvent where u.role.type ='MANAGEREVENT'  ")
	Event findManagerOfEvent(@Param("idEvent") int idEvent );
	
	@Query("Select u FROM User u where u.role.type = 'ADMIN' ")
	User findAdmin();
	
}
