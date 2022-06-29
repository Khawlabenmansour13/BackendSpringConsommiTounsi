package tn.esprit.pidev.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import tn.esprit.pidev.entities.Role;
import tn.esprit.pidev.entities.Roletype;
import tn.esprit.pidev.entities.Sponsor;
import tn.esprit.pidev.entities.TypeSupport;
import tn.esprit.pidev.repositories.IRoleRepository;
import tn.esprit.pidev.repositories.ISponsorRepository;
@Service
public class SponsorExchangeImpl implements ISponsorExchangeService {
    @Autowired
    ISponsorRepository iSponsorRepository;
    
    @Autowired 
    private IEmailService iEmailService;
    @Autowired 
    IRoleRepository iRoleRepository;
	@Override
	public Sponsor sendExchangeSponsor(Sponsor s) {
		
		s.setStatus("PENDING");
	
		iSponsorRepository.save(s);
		return s;
	}

	
	@Override
	public void deleteSponsor(int id) {
		Sponsor s = iSponsorRepository.findById(id).get();
		iSponsorRepository.delete(s);
		
	}

	@Override
	public List<Sponsor> getAllSponsors() {
		List<Sponsor> list = iSponsorRepository.findAll();
		List<Sponsor> res = new ArrayList<>();
		 list.forEach(s -> res.add(s));
		return res ; 
	}

	@Override
	public Sponsor getSponsorById(int id) {
		// TODO Auto-generated method stub
		return iSponsorRepository.findById(id).get();
	}


	@Override
	public boolean acceptExchangeSponsor(int idsponsor) {

		List<Sponsor>results = iSponsorRepository.findAll();
		List<Sponsor>sponsorsStatus = iSponsorRepository.getAllSponsorsByStatusPENDING();
		for(int index = 0 ; index < sponsorsStatus.size() ; index++) {
			System.out.println("====>"+sponsorsStatus.get(index).getIduser());

			if(sponsorsStatus.get(index).getIduser() == idsponsor) {
				sponsorsStatus.get(index).setStatus("ACCEPTED");
				Role role = new Role("Information : sponsor :"+sponsorsStatus.get(index).getFirstname(),Roletype.SPONSOR);

				sponsorsStatus.get(index).setRole(role);
				iRoleRepository.save(role);

				iSponsorRepository.save(sponsorsStatus.get(index));
				
				SimpleMailMessage emailDemandeSponsor = new SimpleMailMessage();
				emailDemandeSponsor.setTo(sponsorsStatus.get(index).getAdress());
				emailDemandeSponsor.setSubject("Do you want to join our application, we will be so happy if you will be able to  help other people");
				emailDemandeSponsor.setText("Detail : \n"
						+" visit you interface to get more detaill");
				
				//sp.setStatus("CONFIRMe");
				//iSponsorService.updateEtatSponsor(sp);
				iEmailService.sendEmail(emailDemandeSponsor);

				return true;
			}
		}
		
		
 		return false;
	}


	@Override
	public boolean rejectExchangeSponsor(int idsponsor) {
		List<Sponsor>sponsorsStatus = iSponsorRepository.getAllSponsorsByStatusPENDING();
		for(int index = 0 ; index < sponsorsStatus.size() ; index++) {
			System.out.println("====>"+sponsorsStatus.get(index).getIduser());

			if(sponsorsStatus.get(index).getIduser() == idsponsor) {
				sponsorsStatus.get(index).setStatus("REJECTED");		
				iSponsorRepository.save(sponsorsStatus.get(index));

				return true;
			}
		}
		return false;
	
		
	}


	@Override
	public int countAcceptedSponsor() {
		// TODO Auto-generated method stub
		return iSponsorRepository.countSponsorACCEPTED();
	}
}
