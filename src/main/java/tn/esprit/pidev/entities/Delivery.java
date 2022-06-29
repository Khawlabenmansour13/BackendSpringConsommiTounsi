package tn.esprit.pidev.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Delivery implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int iddelivery;
	private int Reference;
	private float weight;
	private String Adress;
	private String Region;
	private String City;
	private String etat;
	private String description;
	
	@ManyToOne
	private DeliveryMan deliveryman;
	@OneToOne
	private Command commande;
	
	@OneToOne
	private DeliveryNote deliverynote;

}
