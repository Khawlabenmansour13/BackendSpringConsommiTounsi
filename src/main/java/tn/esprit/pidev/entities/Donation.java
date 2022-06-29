package tn.esprit.pidev.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Donation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDonation;
	@Temporal(TemporalType.DATE)
	private Date date;
	private float amount;
	@ManyToOne
	@JsonIgnore
	private Event event;
	
	@JsonIgnore
	@ManyToOne
	private User user;
	public Donation() {
		super();
	}
	public Donation(int idDonation, Date date, float amount, Event event, User user) {
		super();
		this.idDonation = idDonation;
		this.date = date;
		this.amount = amount;
		this.event = event;
		this.user = user;
	}
	public Donation(Date date, float amount, Event event, User user) {
		super();
		this.date = date;
		this.amount = amount;
		this.event = event;
		this.user = user;
	}
	public int getIdDonation() {
		return idDonation;
	}
	public void setIdDonation(int idDonation) {
		this.idDonation = idDonation;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Donation [idDonation=" + idDonation + ", date=" + date + ", amount=" + amount 
				+ ", user=" + user + "]";
	}


}
