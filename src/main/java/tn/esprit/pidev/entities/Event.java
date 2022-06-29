package tn.esprit.pidev.entities;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Event implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int idEvent;
private String title;
@Temporal(TemporalType.DATE)
private Date date;
@Temporal(TemporalType.TIME)
@DateTimeFormat(style = "hh:mm")
@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="hh:mm")
private Date hour;
private String address;
private String description;
private float discountPercentage;
@Lob
private byte[] image;
private int views;
private int numberOfPlaces;
private boolean status;
private float priceTicket;
private float collAmount;
private int participantsNbr;
private boolean earlyParticipants;
private int nbrTicketEarlyParticipants;
@Enumerated(EnumType.STRING)
private CategoryEvent categoryEvent;
@OneToMany(mappedBy = "event", cascade=CascadeType.ALL)
private List<Donation> donations;
@OneToMany(mappedBy = "event", cascade=CascadeType.ALL)
private List<Advertisement>advertisements;
@OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
private Jackpot jackpot;
@JsonIgnore
@OneToMany(cascade= CascadeType.ALL, mappedBy= "event")
private List<Participation> participations;
@JsonIgnore
@OneToMany(cascade= CascadeType.ALL, mappedBy="event", fetch= FetchType.EAGER)
private List<Notification> notifications;

@JsonIgnore
@OneToMany(cascade= CascadeType.ALL, mappedBy="events")
private List<Meeting> meeting;


@OneToMany(cascade= CascadeType.ALL, mappedBy= "event")
private List<Sponsoring> sponsoring;

@JsonIgnore
@ManyToOne
@JoinColumn(name="id_manage_event")
private User managerEvent; 

public Event() {}


public Event(int idEvent, String title, Date date, Date hour, String address, String description,  byte[] image,
		int views, int numberOfPlaces, boolean status, CategoryEvent categoryEvent, int participantsNbr,float priceTicket,float collAmount,	 float discountPercentage, List<Donation> donations,
		boolean earlyParticipants,int nbrTicketEarlyParticipants, List<Advertisement> advertisements, Jackpot jackpot, List<Participation> participations) {
	super();
	this.idEvent = idEvent;
	this.title = title;
	this.date = date;
	this.hour = hour;
	this.address = address;
	this.participantsNbr = participantsNbr;
	this.description = description;
	this.image = image;
	this.views = views;
	this.collAmount = collAmount;
	this.numberOfPlaces = numberOfPlaces;
	this.status = status;
	this.categoryEvent = categoryEvent;
	this.priceTicket =priceTicket;
	this.donations = donations;
	this.advertisements = advertisements;
	this.jackpot = jackpot;
	this.discountPercentage = discountPercentage;
	this.participations = participations;
	this.earlyParticipants = earlyParticipants;
	this.nbrTicketEarlyParticipants = nbrTicketEarlyParticipants;
	
}

public Event(String title, Date date, Date hour, String address, String description,  byte[] image, int views,
		int numberOfPlaces, boolean status, CategoryEvent categoryEvent,int participantsNbr,float priceTicket,float collAmount,float discountPercentage, List<Donation> donations,
		boolean  earlyParticipants, int nbrTicketEarlyParticipants,List<Advertisement> advertisements, Jackpot jackpot, List<Participation> participations) {
	super();
	this.title = title;
	this.date = date;
	this.hour = hour;
	this.address = address;
	this.description = description;
	this.image = image;
	this.views = views;
	this.numberOfPlaces = numberOfPlaces;
	this.status = status;
	this.categoryEvent = categoryEvent;
	this.collAmount = collAmount;
	this.discountPercentage = discountPercentage;
	this.participantsNbr = participantsNbr;

	this.priceTicket =priceTicket;
	this.donations = donations;
	this.advertisements = advertisements;
	this.jackpot = jackpot;
	this.participations = participations;

	this.earlyParticipants = earlyParticipants;
	this.nbrTicketEarlyParticipants = nbrTicketEarlyParticipants;
}


public int getIdEvent() {
	return idEvent;
}


public void setIdEvent(int idEvent) {
	this.idEvent = idEvent;
}


public String getTitle() {
	return title;
}


public void setTitle(String title) {
	this.title = title;
}


public Date getDate() {
	return date;
}


public List<Meeting> getMeeting() {
	return meeting;
}


public void setMeeting(List<Meeting> meeting) {
	this.meeting = meeting;
}


public void setDate(Date date) {
	this.date = date;
}


public Date getHour() {
	return hour;
}


public void setHour(Date hour) {
	this.hour = hour;
}


public String getAddress() {
	return address;
}


public void setAddress(String address) {
	this.address = address;
}


public String getDescription() {
	return description;
}


public void setDescription(String description) {
	this.description = description;
}


public byte[] getImage() {
	return image;
}



public void setImage(byte[] image) {
	this.image = image;
}

public int getViews() {
	return views;
}


public void setViews(int views) {
	this.views = views;
}


public int getNumberOfPlaces() {
	return numberOfPlaces;
}


public void setNumberOfPlaces(int numberOfPlaces) {
	this.numberOfPlaces = numberOfPlaces;
}

public boolean getIsStatus() {
	return status;
}
public void setStatus(boolean status) {
	this.status = status;
}


public CategoryEvent getCategoryEvent() {
	return categoryEvent;
}


public void setCategoryEvent(CategoryEvent categoryEvent) {
	this.categoryEvent = categoryEvent;
}


public float getPriceTicket() {
	return priceTicket;
}
public void setPriceTicket(float priceTicket) {
	this.priceTicket = priceTicket;
}



public int getParticipantsNbr() {
	return participantsNbr;
}
public void setParticipantsNbr(int participantsNbr) {
	this.participantsNbr = participantsNbr;
}


public boolean isEarlyParticipants() {
	return earlyParticipants;
}
public void setEarlyParticipants(boolean earlyParticipants) {
	this.earlyParticipants = earlyParticipants;
}
public int getNbrTicketEarlyParticipants() {
	return nbrTicketEarlyParticipants;
}
public void setNbrTicketEarlyParticipants(int nbrTicketEarlyParticipants) {
	this.nbrTicketEarlyParticipants = nbrTicketEarlyParticipants;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}
public List<Donation> getDonations() {
	return donations;
}


public void setDonations(List<Donation> donations) {
	this.donations = donations;
}


public List<Advertisement> getAdvertisements() {
	return advertisements;
}


public void setAdvertisements(List<Advertisement> advertisements) {
	this.advertisements = advertisements;
}


public Jackpot getJackpot() {
	return jackpot;
}


public void setJackpot(Jackpot jackpot) {
	this.jackpot = jackpot;
}


public List<Participation> getParticipations() {
	return participations;
}


public float getCollAmount() {
	return collAmount;
}
public void setCollAmount(float collAmount) {
	this.collAmount = collAmount;
}
public boolean isStatus() {
	return status;
}
public void setParticipations(List<Participation> participations) {
	this.participations = participations;
}


public float getDiscountPercentage() {
	return discountPercentage;
}
public void setDiscountPercentage(float discountPercentage) {
	this.discountPercentage = discountPercentage;
}
public List<Notification> getNotifications() {
	return notifications;
}
public void setNotifications(List<Notification> notifications) {
	this.notifications = notifications;
}



public List<Sponsoring> getSponsoring() {
	return sponsoring;
}


public void setSponsoring(List<Sponsoring> sponsoring) {
	this.sponsoring = sponsoring;
}





public User getManagerEvent() {
	return managerEvent;
}


public void setManagerEvent(User managerEvent) {
	this.managerEvent = managerEvent;
}


@Override
public String toString() {
	return "Event [idEvent=" + idEvent + ", title=" + title + ", date=" + date + ", hour=" + hour + ", address="
			+ address + ", description=" + description + ", discountPercentage=" + discountPercentage + ", image="
			+ Arrays.toString(image) + ", views=" + views + ", numberOfPlaces=" + numberOfPlaces + ", status=" + status
			+ ", priceTicket=" + priceTicket + ", collAmount=" + collAmount + ", participantsNbr=" + participantsNbr
			+ ", categoryEvent=" + categoryEvent + ", donations=" + donations + ", advertisements=" + advertisements
			+ ", jackpot=" + jackpot + ", participations=" + participations + ", notifications=" + notifications + "]";
}





}