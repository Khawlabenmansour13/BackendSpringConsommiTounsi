package tn.esprit.pidev.repositories;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.pidev.entities.Product;
import tn.esprit.pidev.entities.Promotion;
@Repository
public interface IPromotionRepository extends CrudRepository<Promotion, Integer>, JpaRepository<Promotion, Integer>{

	/**********************************Return List Current Promotion***************************/
	@Query("SELECT p, Count(*)From Command,  Promotion p WHERE CURRENT_DATE() >= p.startDate and CURRENT_DATE()<= p.endDate")
	public List<Promotion>getCurrentPromotions();
	
	@Query(value="SELECT prod.* FROM Product prod JOIN command_product_products c "
			+ "ON prod.id_product = c.products_id_product "
			+"JOIN stock_products s "
			+"ON s.products_id_product = c.products_id_product WHERE "
			+" :dateExp < DATE_ADD(CURRENT_DATE , INTERVAL :value  DAY ) "  , nativeQuery = true)
	public List<Integer> getRedZonProduct(@Param("value") int value, @Param("dateExp")Date dateExp);
	

	/**********************************get all  promotion ***************************/
	@Query(value="SELECT * FROM Promotion p",nativeQuery = true)
	public List<Promotion>getAllPromotion();
	//and 2020-01-19 > CURRENT_DATE
	/**********************************insert promotion***************************/
	@Modifying
    @Transactional

    @Query(value = "insert into Promotion (libelle,start_date,end_date,percentage,description,product_id_product)"
    		+ " VALUES (:libelle,:startDate,:endDate,:percentage,:description,:product.iProduct)", nativeQuery = true)
    void addCustomPromotion(@Param("libelle") String libelle,
    		@Param("startDate") Date startDate, 
    		@Param("endDate") Date endData,
    		@Param("percentage") float percentage,
    		@Param("description") String description,
    		@Param("product.iProduct")Product p);
}
