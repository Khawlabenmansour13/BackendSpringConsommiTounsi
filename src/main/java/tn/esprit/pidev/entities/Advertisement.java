package tn.esprit.pidev.entities;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Advertisement implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAd;
	private String name;
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Temporal(TemporalType.DATE)
	private Date endDate ;
	private int viewCount;
	private String description;
	@Enumerated(EnumType.STRING)
	private TypeAd ad;
	
	@Lob
	private byte[] image;
	private float cost;
	@ManyToOne
	@JsonIgnore
	private Event event;
	public Advertisement() {
		super();
	}
	public Advertisement(int idAd, String name, Date startDate, Date endDate, int viewCount, String description,
			TypeAd adve, byte[]  image, Event event,float cost) {
		super();
		this.idAd = idAd;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.viewCount = viewCount;
		this.description = description;
		this.ad = adve;
		this.image = image;
		this.event = event;
		this.cost = cost;
	}
	
	public Advertisement(String name, Date startDate, Date endDate, int viewCount, String description, TypeAd ad,
			byte[]  image, Event event,float cost) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.viewCount = viewCount;
		this.description = description;
		this.ad = ad;
		this.image = image;
		this.event = event;
		this.cost = cost;
	}
	public int getIdAd() {
		return idAd;
	}
	public void setIdAd(int idAd) {
		this.idAd = idAd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public TypeAd getAd() {
		return ad;
	}
	public void setAd(TypeAd ad) {
		this.ad = ad;
	}
	public byte[]  getImage() {
		return image;
	}
	public void setImage(byte[]  image) {
		this.image = image;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	@Override
	public String toString() {
		return "Advertisement [idAd=" + idAd + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", viewCount=" + viewCount + ", description=" + description + ", ad=" + ad + ", image=" + image
				+ ", coast=" + cost + ", event=" + event + "]";
	}
	}
	
	
	


