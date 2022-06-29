package tn.esprit.pidev.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.esprit.pidev.entities.Sponsor;

public interface ISponsorRepository extends JpaRepository<Sponsor, Integer>{
	//@Query("SELECT sp FROM Sponsor sp WHERE sp.email = :email")
	//public Sponsor findSponsorByEmail(@Param("email")String email );
	

	
	@Query("SELECT sp FROM Sponsor sp WHERE sp.status ='PENDING'")
	public List<Sponsor> getAllSponsorsByStatusPENDING();


	
	@Query("SELECT COUNT(*) FROM Sponsor sp WHERE sp.status ='ACCEPTED'")
	public int  countSponsorACCEPTED();

}