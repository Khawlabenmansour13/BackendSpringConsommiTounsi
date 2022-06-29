package tn.esprit.pidev.services;

import java.util.List;

import tn.esprit.pidev.entities.Participation;

public interface IParticipationService {

	public String addParticipation(int iduser, int idevent);
	public List<Participation> participationsList();
	public List<Participation> myParticipations();
}
