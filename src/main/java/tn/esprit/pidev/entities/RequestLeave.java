package tn.esprit.pidev.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestLeave implements Serializable{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int idrequest;
@Temporal(TemporalType.DATE)
private Date Startingdate;
@Temporal(TemporalType.DATE)
private Date EndingDate;
private String Etat;




}
