package tn.esprit.pidev.services;
import java.util.Date;
import java.util.List;
import tn.esprit.pidev.entities.Sponsor;
import tn.esprit.pidev.entities.TypeSupport;

public interface ISponsorExchangeService {
	public void deleteSponsor(int id);
	public List<Sponsor> getAllSponsors();
	public Sponsor getSponsorById(int id);
	//public Sponsor findSponsorByEmail(String email);
	public boolean acceptExchangeSponsor(int idsponsor);
	public boolean rejectExchangeSponsor(int idsponsor);
	public Sponsor sendExchangeSponsor(Sponsor s);
	public int countAcceptedSponsor();

    

}
