package tn.esprit.pidev.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idrating;
	private int nbretoile;
	@ManyToOne
	private User user;
	@ManyToOne
	private Publication publication;

}
