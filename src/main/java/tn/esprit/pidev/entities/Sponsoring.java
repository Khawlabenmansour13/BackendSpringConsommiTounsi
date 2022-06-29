package tn.esprit.pidev.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

public class Sponsoring  implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private SponsoringPk sponsoringPk;
	private String productName;
    private int productQuantity;
	private float priceProduct;
	private TypeSupport typeSupport;
	private LocalDateTime dateSponsoring;
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "iduser", referencedColumnName = "iduser", insertable = false, updatable = false)
	@JsonIgnore

	private User sponsor;

	
	@ManyToOne
	@JoinColumn(name = "idEvent", referencedColumnName = "idEvent", insertable = false, updatable = false)
	private Event event;
	
	
	public Sponsoring() {
		super();
	}
	public Sponsoring(SponsoringPk sponsoringPk,String productName, int productQuantity, float priceProduct, TypeSupport typeSupport,
			LocalDateTime dateSponsoring, String status) {
		super();
		this.sponsoringPk = sponsoringPk;
		this.productQuantity = productQuantity;
		this.priceProduct = priceProduct;
		this.productName=productName;
		this.typeSupport = typeSupport;
		this.status = status;
		this.dateSponsoring = dateSponsoring;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public float getPriceProduct() {
		return priceProduct;
	}
	public void setPriceProduct(float priceProduct) {
		this.priceProduct = priceProduct;
	}
	public TypeSupport getTypeSupport() {
		return typeSupport;
	}
	public void setTypeSupport(TypeSupport typeSupport) {
		this.typeSupport = typeSupport;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public SponsoringPk getSponsoringPk() {
		return sponsoringPk;
	}
	public void setSponsoringPk(SponsoringPk sponsoringPk) {
		this.sponsoringPk = sponsoringPk;
	}
	public LocalDateTime getDateSponsoring() {
		return dateSponsoring;
	}
	public void setDateSponsoring(LocalDateTime dateSponsoring) {
		this.dateSponsoring = dateSponsoring;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public User getSponsor() {
		return sponsor;
	}
	public void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	
	
	
}
