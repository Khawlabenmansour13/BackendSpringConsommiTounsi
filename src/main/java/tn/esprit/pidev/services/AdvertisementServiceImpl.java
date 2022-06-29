package tn.esprit.pidev.services;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.entities.Advertisement;
import tn.esprit.pidev.entities.TypeAd;
import tn.esprit.pidev.repositories.IAdvertisementRepository;

@Service
public class AdvertisementServiceImpl implements IAdvertisementService{
	@Autowired
	private IAdvertisementRepository iAdvertisementRepository;
	/**********************Creating add method that insert advertisement into database***************/
	@Override
	public String addAdvertisement(Advertisement adv) {
		
		if(adv.getEndDate().after(adv.getStartDate())) {
		iAdvertisementRepository.save(adv);
		return "added with success";
		}
		return  "end date< start date";
	}
    /*******************Creating deleting method that remove Advertisement by id  from database*********/
	@Override
	public void deleteAdvertisement(int idAdv) {
		Advertisement adver = iAdvertisementRepository.findById(idAdv).get();
		iAdvertisementRepository.delete(adver);
		
	}
	/*******************Creating updating method that remove advertisement by id  from database*********/
	@Override
	public int updateAdvertisement(Advertisement adv, int idAd) {

		return iAdvertisementRepository.updateAdvertisement(adv.getName(), adv.getStartDate(),adv.getEndDate(), adv.getDescription(), 
				adv.getAd(), adv.getImage(),adv.getCost(), idAd);
	}
	/*****************Creating getAll method that retrieve all advertisement from database **************/
	@Override
	public List<Advertisement> getAllAdvertisements() {
		List<Advertisement>advertisements= new ArrayList<Advertisement>();
		iAdvertisementRepository.findAll().forEach(adv ->advertisements.add(adv));
		return advertisements;
	}
	/**************Creating getByid method that retrieve advertisement detail from database************/
	@Override
	public Advertisement getAdvertisementById(int idAd) {
		return iAdvertisementRepository.findById(idAd).get();
	}
	/**********************Creating getByName method that retrieve advertisement**********************/
	@Override
	public Advertisement findAdvertisementByName(String name) {
		return iAdvertisementRepository.findAdvertisementByName(name);
	}
	/****************************AffectedAdCategoryAdvertisement ********************************/
	@Override
	public String affecterAdCategoryAdvertisement(String typeAd, int idadv) {
		Advertisement advertisement = iAdvertisementRepository.findById(idadv).get();
		String msg=" ";
		
		//transformation string vers  enum => CategoryEvent.valueOf(category)
		try {
		for(TypeAd c : TypeAd.values()) {
			if(c == TypeAd.valueOf(typeAd)) {
				advertisement.setAd(TypeAd.valueOf(typeAd));
				iAdvertisementRepository.save(advertisement);
				return msg ="TypeAd Affected successfully!";
				
			}
		}
	}catch(Exception e) {
			 msg="Failed to affected TypeAd";
		}
		return msg;
		
		
	}


	
	
	
}
