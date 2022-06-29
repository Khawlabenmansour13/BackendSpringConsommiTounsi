package tn.esprit.pidev.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "notification")
public class Notification implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "id")
	private int idNotif;
	@Column(name= "subject")
	private String subject;
	@Column(name= "description")
	private String description;
	@Temporal(TemporalType.DATE)
	@Column(name= "date")
	private Date date;
	@Temporal(TemporalType.TIME)
	@Column(name= "time")
	private Date time;
	private String status;
	
	
	

	private String target ;
	
	

	@ManyToOne
	@JoinColumn(name= "id_event")
	private Event event;
	@JsonIgnore 
    @ManyToOne
    private User user;
	public Notification() {
		super();
	}

	public Notification(int idNotif, String subject, String description, Date date, Date time, String status,
			String target, Event event, User user) {
		super();
		this.idNotif = idNotif;
		this.subject = subject;
		this.description = description;
		this.date = date;
		this.time = time;
		this.status = status;
		this.target = target;
		this.event = event;
		this.user = user;
	}

	public int getIdNotif() {
		return idNotif;
	}

	public void setIdNotif(int idNotif) {
		this.idNotif = idNotif;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return "Notification [idNotif=" + idNotif + ", subject=" + subject + ", description=" + description + ", date="
				+ date + ", time=" + time + ", event=" + event + "]";
	}
	
}
