package tn.esprit.pidev.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class SponsoringPk implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int iduser;
	private int idEvent;

	
	
	
	public SponsoringPk() {
		super();
	}
	
	
	
	
	public SponsoringPk(int iduser, int idEvent) {
		super();
		this.iduser = iduser;
		this.idEvent = idEvent;
		
	}




	public int getIduser() {
		return iduser;
	}




	public void setIduser(int iduser) {
		this.iduser = iduser;
	}




	public int getIdEvent() {
		return idEvent;
	}




	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	
}
