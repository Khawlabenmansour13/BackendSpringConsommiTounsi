package tn.esprit.pidev.services;
import java.util.List;
import tn.esprit.pidev.entities.Advertisement;
public interface IAdvertisementService {
	   public String addAdvertisement(Advertisement adv);
	   public void deleteAdvertisement(int idAd);
	   public int updateAdvertisement(Advertisement adv , int idAd);
	   public List<Advertisement> getAllAdvertisements();
	   public Advertisement getAdvertisementById(int idAd);
	   public Advertisement findAdvertisementByName(String name);
       public String affecterAdCategoryAdvertisement(String typeAd, int idadv);

}
