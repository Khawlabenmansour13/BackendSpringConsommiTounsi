package tn.esprit.pidev.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryNote implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int numorder;
	private float shippingcost;
	private float Total;
	@OneToOne
	private Command command;
	@OneToOne(mappedBy = "deliverynote")
	private Delivery delivery;
	
	

}
