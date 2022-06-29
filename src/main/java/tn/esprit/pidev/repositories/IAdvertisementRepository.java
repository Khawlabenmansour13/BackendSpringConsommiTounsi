package tn.esprit.pidev.repositories;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.entities.Advertisement;
import tn.esprit.pidev.entities.TypeAd;

@Repository
public interface IAdvertisementRepository extends CrudRepository<Advertisement, Integer> {
	/***************************AdvertisementByName**************************************/
	@Query("SELECT a FROM Advertisement a WHERE a.name = :name")
	 public Advertisement findAdvertisementByName(@Param("name")String name);
	/*******************************UpdateAdvertisement*******************************************/
	@Modifying
	@Transactional
	@Query("UPDATE Advertisement e SET e.name = :name  , e.startDate= :startDate , e.endDate= :endDate , e.description =:description  "
			+ ", e.ad = :ad , e.image = :image, e.cost =:cost"
			+ " WHERE e.idAd = :idAd")
	
	public int updateAdvertisement(@Param("name")String name,@Param("startDate")Date startDate,@Param("endDate")Date endDate,@Param("description")String description,
			@Param("ad")TypeAd ad,@Param("image")byte[] image,
			@Param("cost")float cost,@Param("idAd")int idAd);
}
