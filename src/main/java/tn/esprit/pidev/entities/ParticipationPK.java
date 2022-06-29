package tn.esprit.pidev.entities;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable

public class ParticipationPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idUser;
	private int idEvent;
	//private int number;
	
	
	
	public ParticipationPK() {
		super();
	}
	public ParticipationPK(int idUser, int idEvent) {
		super();
		this.idUser = idUser;
		this.idEvent = idEvent;
		//this.number = number;
	}
	
	
	/*public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}*/
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdEvent() {
		return idEvent;
	}
	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}
	@Override
	public String toString() {
		return "ParticipationPK [idUser=" + idUser + ", idEvent=" + idEvent + "]";
	}
	
	
	
}
