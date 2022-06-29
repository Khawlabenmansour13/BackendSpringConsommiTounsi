package tn.esprit.pidev.entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int iduser;
	private String Lastname;
	private String Firstname;
	private String Adress;
	private String Login;
	private String password;
	private int nbrpoint;
	private String Tel;
	private float accBalance;
	@Temporal(TemporalType.DATE)
	private Date DateCreation;
	@Lob
	private byte[] picture;
	@JsonIgnore
	@ManyToOne
	private Role role;
	@OneToOne
	private Contrat contrat;
    @OneToMany(mappedBy = "user")
	private List<Claim>claims;
    @OneToMany(mappedBy = "user")
	private List<Stock> stocks;
	@OneToMany(mappedBy="user")
	private List<Donation> donation;
	@OneToMany(mappedBy="user")
	private List<Cart> carts;
	@OneToMany(cascade= CascadeType.ALL, mappedBy= "user")
	private List<Participation> participations;
	@OneToMany(mappedBy = "user")
	private List<Commentaire> commentaire;
	@OneToMany(mappedBy = "user")
	private List <Rating> rating;
	@OneToOne(mappedBy = "user")
	private Command command;
	@OneToOne(mappedBy = "user")
	private Bill bill;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private List<Notification> notification;
	
 
	@OneToMany(cascade=CascadeType.ALL, mappedBy="sponsor")
	@JsonIgnore

	private List<Sponsoring> sponsoring;
	

	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private List<Evaluation>evaluations;	

	@OneToMany(cascade=CascadeType.ALL, mappedBy="managerEvent")
	private List<Event>events;	

	public List<Sponsoring> getSponsoring() {
		return sponsoring;
	}




	public void setSponsoring(List<Sponsoring> sponsoring) {
		this.sponsoring = sponsoring;
	}




	//New 
@JsonIgnore
	  @OneToMany(cascade = CascadeType.ALL,mappedBy = "users")
	    private List<Meeting> meetings;
	  
   
	public User() {
		
	}




	public User( String lastname, String firstname, String adress, String login, String password,
			int nbrpoint, String tel, float accBalance, Date dateCreation, byte[] picture, Role role, Contrat contrat,
			List<Claim> claims, List<Stock> stocks, List<Donation> donation, List<Cart> carts,
			List<Participation> participations, List<Commentaire> commentaire, List<Rating> rating, Command command,
			Bill bill, List<Notification> notification) {
		super();
		Lastname = lastname;
		Firstname = firstname;
		Adress = adress;
		Login = login;
		this.password = password;
		this.nbrpoint = nbrpoint;
		Tel = tel;
		this.accBalance = accBalance;
		DateCreation = dateCreation;
		this.picture = picture;
		this.role = role;
		this.contrat = contrat;
		this.claims = claims;
		this.stocks = stocks;
		this.donation = donation;
		this.carts = carts;
		this.participations = participations;
		this.commentaire = commentaire;
		this.rating = rating;
		this.command = command;
		this.bill = bill;
		this.notification = notification;
	}




	public int getIduser() {
		return iduser;
	}




	public void setIduser(int iduser) {
		this.iduser = iduser;
	}




	public String getLastname() {
		return Lastname;
	}




	public List<Meeting> getMeetings() {
		return meetings;
	}




	public void setMeetings(List<Meeting> meetings) {
		this.meetings = meetings;
	}




	public void setLastname(String lastname) {
		Lastname = lastname;
	}




	public String getFirstname() {
		return Firstname;
	}




	public void setFirstname(String firstname) {
		Firstname = firstname;
	}




	public String getAdress() {
		return Adress;
	}




	public void setAdress(String adress) {
		Adress = adress;
	}




	public String getLogin() {
		return Login;
	}




	public void setLogin(String login) {
		Login = login;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public int getNbrpoint() {
		return nbrpoint;
	}




	public void setNbrpoint(int nbrpoint) {
		this.nbrpoint = nbrpoint;
	}




	public String getTel() {
		return Tel;
	}




	public void setTel(String tel) {
		Tel = tel;
	}




	public float getAccBalance() {
		return accBalance;
	}




	public void setAccBalance(float accBalance) {
		this.accBalance = accBalance;
	}




	public Date getDateCreation() {
		return DateCreation;
	}




	public void setDateCreation(Date dateCreation) {
		DateCreation = dateCreation;
	}




	public byte[] getPicture() {
		return picture;
	}




	public void setPicture(byte[] picture) {
		this.picture = picture;
	}




	public Role getRole() {
		return role;
	}




	public void setRole(Role role) {
		this.role = role;
	}




	public Contrat getContrat() {
		return contrat;
	}




	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}




	public List<Claim> getClaims() {
		return claims;
	}




	public void setClaims(List<Claim> claims) {
		this.claims = claims;
	}




	public List<Stock> getStocks() {
		return stocks;
	}




	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}




	public List<Donation> getDonation() {
		return donation;
	}




	public void setDonation(List<Donation> donation) {
		this.donation = donation;
	}




	public List<Cart> getCarts() {
		return carts;
	}




	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}




	public List<Participation> getParticipations() {
		return participations;
	}




	public void setParticipations(List<Participation> participations) {
		this.participations = participations;
	}




	public List<Commentaire> getCommentaire() {
		return commentaire;
	}




	public void setCommentaire(List<Commentaire> commentaire) {
		this.commentaire = commentaire;
	}




	public List<Rating> getRating() {
		return rating;
	}




	public void setRating(List<Rating> rating) {
		this.rating = rating;
	}




	public Command getCommand() {
		return command;
	}




	public void setCommand(Command command) {
		this.command = command;
	}




	public Bill getBill() {
		return bill;
	}




	public void setBill(Bill bill) {
		this.bill = bill;
	}




	public List<Notification> getNotification() {
		return notification;
	}




	public void setNotification(List<Notification> notification) {
		this.notification = notification;
	}







	public List<Evaluation> getEvaluations() {
		return evaluations;
	}




	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}




	public User(int iduser, String lastname, String firstname, String adress, String login, String password,
			int nbrpoint, String tel, float accBalance, Date dateCreation, byte[] picture, Role role, Contrat contrat,
			List<Claim> claims, List<Stock> stocks, List<Donation> donation, List<Cart> carts,
			List<Participation> participations, List<Commentaire> commentaire, List<Rating> rating, Command command,
			Bill bill, List<Notification> notification) {
		super();
		this.iduser = iduser;
		Lastname = lastname;
		Firstname = firstname;
		Adress = adress;
		Login = login;
		this.password = password;
		this.nbrpoint = nbrpoint;
		Tel = tel;
		this.accBalance = accBalance;
		DateCreation = dateCreation;
		this.picture = picture;
		this.role = role;
		this.contrat = contrat;
		this.claims = claims;
		this.stocks = stocks;
		this.donation = donation;
		this.carts = carts;
		this.participations = participations;
		this.commentaire = commentaire;
		this.rating = rating;
		this.command = command;
		this.bill = bill;
		this.notification = notification;
	}








	public List<Event> getEvents() {
		return events;
	}




	public void setEvents(List<Event> events) {
		this.events = events;
	}




	@Override
	public String toString() {
		return "User [iduser=" + iduser + ", Lastname=" + Lastname + ", Firstname=" + Firstname + ", Adress=" + Adress
				+ ", Login=" + Login + ", password=" + password + ", nbrpoint=" + nbrpoint + ", Tel=" + Tel
				+ ", accBalance=" + accBalance + ", DateCreation=" + DateCreation + ", picture="
				+ Arrays.toString(picture) + ", role=" + role + ", contrat=" + contrat + ", claims=" + claims
				+ ", stocks=" + stocks + ", donation=" + donation + ", carts=" + carts + ", participations="
				+ participations + ", commentaire=" + commentaire + ", rating=" + rating + ", command=" + command
				+ ", bill=" + bill + ", notification=" + notification + "]";
	}
	
}
