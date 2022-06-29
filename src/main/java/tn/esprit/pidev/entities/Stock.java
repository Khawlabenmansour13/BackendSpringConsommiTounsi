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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity

public class Stock implements Serializable{
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idstock;
	@Temporal(TemporalType.DATE)
	private Date DateCreation;
	private float Quantity;
	@ManyToOne
	private User user;
	@ManyToMany
	private List<Product> products;
	
	
	@Temporal(TemporalType.DATE)
	private Date dateExp	;
	

	public Stock() {
		super();
	}



	
	public Stock(int idstock, Date dateCreation, float quantity, User user, List<Product> products, Date dateExp) {
		super();
		this.idstock = idstock;
		DateCreation = dateCreation;
		Quantity = quantity;
		this.user = user;
		this.products = products;
		this.dateExp = dateExp;
	}
	
	
	public Stock(Date dateCreation, float quantity, User user, List<Product> products, Date dateExp) {
		super();
		DateCreation = dateCreation;
		Quantity = quantity;
		this.user = user;
		this.products = products;
		this.dateExp = dateExp;
	}


	public Date getDateExp() {
		return dateExp;
	}




	public void setDateExp(Date dateExp) {
		this.dateExp = dateExp;
	}




	public int getIdstock() {
		return idstock;
	}
	public void setIdstock(int idstock) {
		this.idstock = idstock;
	}
	public Date getDateCreation() {
		return DateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		DateCreation = dateCreation;
	}
	public float getQuantity() {
		return Quantity;
	}
	public void setQuantity(float quantity) {
		Quantity = quantity;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	

}
