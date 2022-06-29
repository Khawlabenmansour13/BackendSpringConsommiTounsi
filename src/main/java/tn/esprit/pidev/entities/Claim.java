package tn.esprit.pidev.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class Claim implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idclaim;
	private int reference;
	private String description;
	@Temporal(TemporalType.DATE)
	private Date dateclaim;
	
	private String Etat;
	@ManyToOne
	private User user;
	@OneToOne
	private Refund refund;
	@OneToOne
	private Repair repair;
	@OneToOne
	private Exchange exchange;
	@OneToOne
	private Bill bill;
	

}
