package tn.esprit.pidev.services;

import tn.esprit.pidev.entities.Event;
import tn.esprit.pidev.entities.Jackpot;

public interface IJackpotService {
	
	public Jackpot addJackpot();
	public Jackpot findJackpot(Event event);

}
