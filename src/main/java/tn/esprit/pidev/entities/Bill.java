package tn.esprit.pidev.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBill;
	private int numBill;
	@Temporal(TemporalType.DATE)
	private Date DateBill;
	private float totalfinal;
	private Date datereglement;
	private TypeFacture tupefac;
	@OneToOne(mappedBy = "bill")
	private Claim claim;
	@OneToOne
	private Command command;
	@OneToOne
	private User user;

}
