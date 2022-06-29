package tn.esprit.pidev.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Jackpot implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idJack;
	private double total;
	
	public Jackpot() {
		super();
	}
	public Jackpot(int idJack, double total) {
		super();
		this.idJack = idJack;
		this.total = total;
		
	}
	public Jackpot(double total) {
		super();
		this.total = total;

	}
	public int getIdJack() {
		return idJack;
	}
	public void setIdJack(int idJack) {
		this.idJack = idJack;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "Jackpot [idJack=" + idJack + ", total=" + total + "]";
	}
	
	
	
	
	

}
