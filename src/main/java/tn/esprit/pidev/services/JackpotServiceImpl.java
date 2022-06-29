package tn.esprit.pidev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pidev.entities.Advertisement;
import tn.esprit.pidev.entities.Event;
import tn.esprit.pidev.entities.Jackpot;
import tn.esprit.pidev.repositories.IJackpotRepository;

@Service
public class JackpotServiceImpl  implements IJackpotService{
	@Autowired
	private IJackpotRepository iJackpotRepository;
	/**********************AddJackpot***************************/
	@Override
	public Jackpot addJackpot() {
		Jackpot j = new Jackpot();
		j.setTotal(0);;
		return iJackpotRepository.save(j);
   
		
	}
	/*********************FindEventJackpot******************************/
	@Override
	public Jackpot findJackpot(Event event) {
		Jackpot jackpot = event.getJackpot();
		return jackpot;
	}
	
	
	


}
