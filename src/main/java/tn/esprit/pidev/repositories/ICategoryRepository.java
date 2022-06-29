package tn.esprit.pidev.repositories;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.pidev.entities.Category;

@Repository
public interface ICategoryRepository extends CrudRepository<Category,Integer> {
	/**********************************Update category ***************************/
	@Transactional
	@Modifying
	@Query("UPDATE Category  c SET c.name = :name WHERE c.id =:id")
	public void updateCateogry(@Param("name")String name,@Param("id")int id);
	/**********************************Find category ByName***************************/
	@Query("SELECT cat from Category cat WHERE cat.name =:name")
	public Category findCategoryByName(@Param("name")String name);
	/**********************************Find product ByName***************************/
	@Query("SELECT cat from Category cat WHERE cat.name =:name")
	public Category findProductByName(@Param("name")String name);

	
	
}
