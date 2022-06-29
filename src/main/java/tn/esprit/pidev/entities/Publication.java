package tn.esprit.pidev.entities;

import java.io.Serializable;
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
public class Publication implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int idpub;
private String topic;
@OneToMany(mappedBy = "publication")
private List <Commentaire> commentaire;
@OneToMany(mappedBy = "publication")
private List<Rating>rating;
}
