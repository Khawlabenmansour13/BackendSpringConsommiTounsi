package tn.esprit.pidev.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name= "evaluation")
public class Evaluation implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idEvalution ;
	
	private int rate;
	
	
	@ManyToOne
	private  User user;
	
	@ManyToOne
	private Product product;
	
	
	public Evaluation() {
		
	}


	public Evaluation(int idEvalution, int rate, User user, Product product) {
		super();
		this.idEvalution = idEvalution;
		this.rate = rate;
		this.user = user;
		this.product = product;
	}


	public int getIdEvalution() {
		return idEvalution;
	}


	public void setIdEvalution(int idEvalution) {
		this.idEvalution = idEvalution;
	}


	

	

	public int getRate() {
		return rate;
	}


	public void setRate(int rate) {
		this.rate = rate;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}

	


	
}
