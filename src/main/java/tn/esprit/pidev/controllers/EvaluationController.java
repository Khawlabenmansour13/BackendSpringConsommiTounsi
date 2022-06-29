package tn.esprit.pidev.controllers;


import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pidev.services.IEvaluationService;

@RestController
public class EvaluationController {
	@Autowired
	IEvaluationService iEvaluationService;
	private static final Logger logger = LoggerFactory.getLogger(EvaluationController.class);

    @PostMapping("/Evaluation/add-evaluation/{idproduct}/{iduser}/{rate}")  
	@ResponseBody()
    public String  EvaluateProduct(@PathVariable("idproduct") int idProduct,
			@PathVariable("iduser") int idUser, @PathVariable("rate") int rate
	)   
	{  
		return  iEvaluationService.evaluateProduct(idUser, idProduct, rate);
	}
    
    @PutMapping("/Evaluation/update-evaluation/{idproduct}/{iduser}/{idrate}/{rate}")  
	@ResponseBody()
    public  String UpdateEvaluate(@PathVariable("idproduct") int idProduct,
			@PathVariable("iduser") int idUser,@PathVariable("idrate") int idrate,@PathVariable("rate") int rate)   
	{  
		return  iEvaluationService.updateRating(idUser, idProduct,idrate ,rate);
	}
	
	@GetMapping("/Evaluation/countRatingUserByProduct/{idp}")
	public int count(@PathVariable("idp")int idp) {
		return iEvaluationService.countRatingUserByProduct(idp);
	}
	
	

	
	// "Find recommended products for user using KNN Algorithm"//
    @GetMapping(value = "/Evaluation/recommended", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getRecommendedOroductsByUserId(@RequestParam("User_id") int User_id)
           throws JSONException {
       // if (!userRservice.existsById(User_id)) {
         //   throw new ResourceNotFoundException("User not found!");
        //}

        String recommendedProducts= iEvaluationService.recommendedProducts(User_id);
    	logger.debug("inside ProductController.getRecommendedProductsByUserId() method");

        return ResponseEntity.ok().body(recommendedProducts);
    }

	

}
