package tn.esprit.pidev.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryMan implements Serializable{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int iddeliveryman;
private String workzone;
private Boolean availability;
private int Totnbdelivery;
private float premium;

@OneToMany
private List<RequestLeave>requestleave;
@OneToMany
private List<ExtraTime>extratimes;





}
