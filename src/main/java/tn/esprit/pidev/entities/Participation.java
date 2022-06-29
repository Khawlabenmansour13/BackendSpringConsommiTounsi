package tn.esprit.pidev.entities;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "participation")
public class Participation implements Serializable {
	
	private static final long serialVersionUID = 1L;
    @EmbeddedId
	private ParticipationPK participationPK;
	private float price;
	
	@Temporal(TemporalType.DATE)
	private Date participationDate;

	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "idUser", referencedColumnName = "iduser", insertable = false, updatable = false)

	private User user;
	@ManyToOne
	@JoinColumn(name = "idEvent", referencedColumnName = "idEvent", insertable = false, updatable = false)
	private Event event;

	public Participation() {
		super();
	}

	public Participation(ParticipationPK participationPK, float price, Date participationDate, User user,
			Event event) {
		super();
		this.participationPK = participationPK;
		this.price = price;
		this.participationDate = participationDate;
		this.user = user;
		this.event = event;
	}

	public ParticipationPK getParticipationPK() {
		return participationPK;
	}

	public void setParticipationPK(ParticipationPK participationPK) {
		this.participationPK = participationPK;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getParticipationDate() {
		return participationDate;
	}

	public void setParticipationDate(Date participationDate) {
		this.participationDate = participationDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return "Participation [participationPK=" + participationPK + ", price=" + price + ", participationDate="
				+ participationDate + ", user=" + user + "]";
	}
}

	
	
	
	
	
