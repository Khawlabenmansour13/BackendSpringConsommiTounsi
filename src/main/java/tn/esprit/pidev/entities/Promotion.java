package tn.esprit.pidev.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Promotion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPromotion;
	private String libelle;
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Temporal(TemporalType.DATE)
	private Date endDate;
	private String description;
	private float percentage;
	@ManyToOne
	private Product product;
	
	
	

    public Promotion() {
		super();
	}
    
    public Promotion(int idPromotion, String libelle, Date startDate, Date endDate, String description, float percentage,
			Product product) {
		super();
		this.idPromotion = idPromotion;
		this.libelle = libelle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.percentage = percentage;
		this.product = product;
	}

	public Promotion(String libelle, Date startDate, Date endDate, String description, float percentage,
			Product product) {
		super();
		this.libelle = libelle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.percentage = percentage;
		this.product = product;
	}

	public int getIdPromotion() {
		return idPromotion;
	}



	public void setIdPromotion(int idPromotion) {
		this.idPromotion = idPromotion;
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



	public String getLibelle() {
		return libelle;
	}



	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public float getPercentage() {
		return percentage;
	}



	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

    public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Promotion [idPromotion=" + idPromotion + ", libelle=" + libelle + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", description=" + description + ", percentage=" + percentage + ", product="
				+ product + "]";
	}



	
	
}
