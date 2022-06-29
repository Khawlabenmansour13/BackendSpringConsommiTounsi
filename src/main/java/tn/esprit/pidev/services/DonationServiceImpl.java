package tn.esprit.pidev.services;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.entities.Event;
import tn.esprit.pidev.entities.Jackpot;
import tn.esprit.pidev.entities.Roletype;
import tn.esprit.pidev.entities.User;
import tn.esprit.pidev.repositories.IDonationRepository;
import tn.esprit.pidev.repositories.IEventRepository;
import tn.esprit.pidev.repositories.IJackpotRepository;
import tn.esprit.pidev.repositories.IUserRepository;
import tn.esprit.pidev.entities.Donation;
@Service
public class DonationServiceImpl implements IDonationService {
    @Autowired
	IEventRepository iEventRepository;
	@Autowired
	IJackpotRepository iJackpotRepository;
	@Autowired
	IDonationRepository iDonationRepository;
	@Autowired
	IUserRepository iUserRepository;
	@Autowired
	 PayementServiceImpl iPayementService;
	@Override
	public String Donation(int eid,int uid, float amount) {
		float totale=0;
		float newCollAmount=0;
		Donation donation = new Donation();
		Event ev = iEventRepository.findById(eid).get();
		User user = iUserRepository.findById(uid).get();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		//attribut static  dans l'user 
		Jackpot jackpot = new Jackpot();
		
		if(user.getRole().getType().equals(Roletype.ADMIN) ||user.getRole().getType().equals(Roletype.SPONSOR))
			return "Unauthorizied to donate";
		if(date.getTime() - ev.getDate().getTime() >0)
			return "Event finished you can't donate  ";
	
		System.out.println(ev.getJackpot());
		if(user.getAccBalance() >=amount) {
			user.setAccBalance(user.getAccBalance() - amount);
			Jackpot j = iJackpotRepository.findJackpotEvent(ev.getIdEvent());
			j.setTotal(j.getTotal() + amount);
			ev.setCollAmount(ev.getCollAmount() + amount);
			donation.setAmount(amount);
			donation.setDate(date);
			donation.setEvent(ev);
			donation.setUser(user);
			iUserRepository.save(user);
			iDonationRepository.save(donation);
			iJackpotRepository.save(j);
			iEventRepository.save(ev);
			/*totale = user.getAccBalance() - amount;
			jackpot = iEventRepository.findJackpot(ev.getJackpot());
			System.out.println("sum="+jackpot.getTotal());
			donation.setAmount(amount);
			donation.setDate(date);
			donation.setEvent(ev);
			donation.setUser(user);
			jackpot.setTotal(jackpot.getTotal()+amount);
			iJackpotRepository.save(jackpot);
			iEventRepository.save(ev);
            iDonationRepository.save(donation);*/
			iPayementService.chargeCustomer((int)amount);

			JSONArray productJsonArray = new JSONArray();
    		try {
	    		JSONObject productJson = new JSONObject("{}");

			productJson.put("Donation"," added successfully");
			productJson.put("donate to   event",ev.getTitle());
			productJson.put("Donation name user",user.getFirstname());
			productJson.put("user donate amount of ",amount);
			productJson.put("user accurance balance ",user.getAccBalance());
					productJson.put("Added by","User");
			
			 productJsonArray.put(productJson);

			 return productJsonArray.toString();
		}catch(Exception e ){
			e.printStackTrace();
		}
		}
		JSONArray productJsonArray = new JSONArray();
		try {
    		JSONObject productJson = new JSONObject("{}");

		productJson.put("Donation","Failed!! ");
		productJson.put("Response","Balance amount below than amount of donation we are sorry ");
		productJson.put("donate to   event",ev.getTitle());
		productJson.put("Donation name user",user.getFirstname());
		productJson.put("user donate amount of ",amount);
		productJson.put("user accurance balance ",user.getAccBalance());
				productJson.put("Added by","User");
				  productJsonArray.put(productJson);

		 
	}catch(Exception e ){
		e.printStackTrace();
	}

		 return productJsonArray.toString();

	}


	@Override
	public List<Donation> getDonationEvention(Event event) {
		List<Donation> list = iDonationRepository.DonationOfEvent(event);
		return list;
	}

	@Override
	public List<Donation> getHistoryDonation() {
		return null;
		/*List<Donation> list = iDonationRepository.DonationOfUser(UserController.USERCONNECTED);
		return list;*/
	}

}
