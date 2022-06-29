package tn.esprit.pidev.services;

import org.json.JSONException;
import org.springframework.http.ResponseEntity;

import tn.esprit.pidev.repositories.IProductRepository;
import tn.esprit.pidev.repositories.IUserRepository;

public interface IEvaluationService {

	public  String evaluateProduct(int idUser ,int idProduct ,int rate);
    public String updateRating(int iduser, int idproduct, int idRate,  int rate);
	public int countRatingUserByProduct(int idp);
	String recommendedProducts(int userId) throws JSONException;	
	
}
