   package tn.esprit.pidev.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pidev.entities.Promotion;
import tn.esprit.pidev.services.IPromotionService;

@RestController
public class PromotionController {
    @Autowired
	IPromotionService iPromotionService;
   //creating post mapping method that insert promotion into database
	@PostMapping("/promotion/add-promotion")
	@ResponseBody()
	public int  addPromotion(@RequestBody Promotion p) {
		 iPromotionService.addPromos(p);
		 return p.getIdPromotion();
			
		}
	//creating put mapping that updates the promotion detail 
	@PutMapping("/promotion/update-promotion/{id-promotion}")
	@ResponseBody
	public ResponseEntity<String> updatePromotion(
		@RequestBody Promotion promotion,@PathVariable("id-promotion")int idPromotion) {
		iPromotionService.updatePromos(promotion, idPromotion);
		return new ResponseEntity<String>("Promotion updated successfully",HttpStatus.OK);
			
		}
	//creating a get mapping that retrieves all the get-current-promotions detail from the database  
	@GetMapping("/promotion/get-current-promotions")
	@ResponseBody
	public List<Promotion>getCurrentPromotions() {
		return iPromotionService.getPromosByDate();
		}
	//creating a put mapping that affected-product-promotions from the database 
	@PutMapping("/promotion/affecter-product-promotion/{idproduct}/{idpromotion}")
	@ResponseBody
		public String affectProductPromotion(@PathVariable("idproduct")int idproduct, @PathVariable("idpromotion")int idpromotion) {
			iPromotionService.affecterProductPromotion(idproduct, idpromotion);
			return "Product affected to Promotion with success!!";
		}
	//creating a delete mapping that delete data from database
	@DeleteMapping("/promotion/delete-promotion/{idPromotion}")
	@ResponseBody
		public ResponseEntity<String>deletePromotion(
			@RequestBody Promotion promotion,@PathVariable("idPromotion")int idPromotion) {
		    iPromotionService.deletePromos(idPromotion);
			return new ResponseEntity<String>("promotion deleted successfully",HttpStatus.ACCEPTED);
				
			}
	//creating a get mapping that retrieves a specific promotion
	@GetMapping("/promotion/get-promotionbyId/{idPromotion}")
	@ResponseBody
		public Promotion getPromotionById(@PathVariable("idPromotion")int idPromotion) {
				return iPromotionService.getPromosById(idPromotion);
	
	}
	//creating a get mapping that retrieves all the promotion detail from the database   
		@GetMapping("/promotion/get-all-promotion")  
		private List<Promotion> getAllPromos()   
		{  
			return iPromotionService.getAllPromos();  
		}  
		//creating a get mapping that retrieves a specific promotion
		 @PostMapping("/promotion/proposePromos/{idstock}")
		 @ResponseBody
		 public String getPromotionRed(@RequestBody Promotion p,@PathVariable("idstock") int idstock) {
			 
					return  iPromotionService.proposePromos(p,idstock);
				}
		 
			@PutMapping("/promotion/reduction-product/{id}")  
			private String reductionProductPromotion(@PathVariable("id")int  id)   
			{  
				return iPromotionService.discountProductPromotion(id);  
			} 
		 
	
}