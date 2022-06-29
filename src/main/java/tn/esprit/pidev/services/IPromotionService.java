package tn.esprit.pidev.services;
import java.util.List;
import tn.esprit.pidev.entities.Promotion;

public interface IPromotionService {
	public int addPromos(Promotion p);
	public void updatePromos(Promotion p,int idPromotion);
	public int deletePromos(int idPromotion);
	public Promotion getPromosById(int idPromotion);
	public List<Promotion> getAllPromos();
	public List<Promotion > getPromosByDate();
	public void affecterProductPromotion(int idproduct,int idpromotion);
	public String discountProductPromotion(int id);
	String proposePromos(Promotion promotion, int ids);
}
