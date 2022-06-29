package tn.esprit.pidev.services;
import java.util.List;

import tn.esprit.pidev.entities.Event;
import tn.esprit.pidev.entities.Donation;

public interface IDonationService {
	public List<Donation> getDonationEvention(Event event) ;
	public List<Donation> getHistoryDonation();
	public String Donation(int eid,int uid, float amount);
}
