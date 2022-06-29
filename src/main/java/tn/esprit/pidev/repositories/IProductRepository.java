package tn.esprit.pidev.repositories;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.entities.Product;
@Repository
public interface IProductRepository extends CrudRepository<Product, Integer>{

	/**********************************Find product ByName******************************/
	@Query("SELECT product FROM Product product WHERE product.productName = :productName")
	public Product findProductByName(@Param("productName")String productName);
	
	/**********************************Find product ById******************************/
	@Query("SELECT product FROM Product product WHERE product.idProduct = :id")
	public Product findProductById(@Param("id")int id);
	/**********************************Find product ByCategory***************************/
	@Query("SELECT prod FROM Product prod JOIN Category cat ON prod.category.name = :name")
	public List<Product> filterProductByCategory(@Param("name")String category);
	
	/**********************************Find product ByCategory***************************/
	@Query("SELECT product FROM Product product WHERE product.barCode = :barCode")
	public Product findProductByBarCode(@Param("barCode")String barCode);
    /**********************************Find product Bymost view ***************************/
	@Modifying
	@Transactional
	@Query("UPDATE Product p   SET p.mostViewed = :view+1 WHERE p.id =:id ")
	public int updateViewCountProduct(@Param("view")int view,@Param("id")int id);

	
	/*@Query(value = "select p.* from Product p where ps.expiration_date < DATE_ADD(CURRENT_DATE, INTERVAL :value DAY) and ps.expiration_date > CURRENT_DATE   ", nativeQuery = true)
	public List<Product> getRedZonProduct(@Param("value") int value);*/
	
	

	
}
