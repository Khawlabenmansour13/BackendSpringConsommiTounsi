package tn.esprit.pidev.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

public class Command implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idcommand;
	private double AmountCommand;
	@Temporal(TemporalType.DATE)
	private Date DateCreation;
	@Temporal(TemporalType.DATE)
	private Date DateSend;
	private String Etat;
	private int numcommand;
	private int tva;
	@Enumerated(EnumType.STRING)
	private ModePayement payement;
	private Boolean validpayement;
	@OneToOne
	private Cart cart;
	@OneToOne
	private Transaction transaction;
	@OneToOne(mappedBy = "command")
	private Bill bill;
	@OneToOne
	private User user;
@OneToOne(mappedBy = "commande")
	private Delivery delivery;

public Command() {
	super();
}

public Command(double amountCommand, Date dateCommand, Date dateCreation, Date dateSend, int numsend, int numcommand,
		int tva, ModePayement payement) {
	super();
	AmountCommand = amountCommand;
	
	DateCreation = dateCreation;
	DateSend = dateSend;
	
	this.numcommand = numcommand;
	this.tva = tva;
	this.payement = payement;
}

public Command(int idcommand, double amountCommand, Date dateCreation, Date dateSend, String etat, int numcommand,
		int tva, ModePayement payement, Boolean validpayement, Cart cart, Transaction transaction, Bill bill, User user,
		Delivery delivery) {
	super();
	this.idcommand = idcommand;
	AmountCommand = amountCommand;
	DateCreation = dateCreation;
	DateSend = dateSend;
	Etat = etat;
	this.numcommand = numcommand;
	this.tva = tva;
	this.payement = payement;
	this.validpayement = validpayement;
	this.cart = cart;
	this.transaction = transaction;
	this.bill = bill;
	this.user = user;
	this.delivery = delivery;
}

	

}
