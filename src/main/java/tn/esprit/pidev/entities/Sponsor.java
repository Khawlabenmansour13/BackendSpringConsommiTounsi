package tn.esprit.pidev.entities;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;

import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table

public class Sponsor extends User	 implements Serializable {
     
	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
    @Column(name= "brand")
	private String brand;
    
    private String url;
	private String status;


	public String getUrl() {
		return url;
	}





	public void setUrl(String url) {
		this.url = url;
	}





	public String getStatus() {
		return status;
	}





	public void setStatus(String status) {
		this.status = status;
	}


	
		
	public Sponsor() {
		super();
	
	}


	
	public Sponsor(String brand, String url, String status) {
		super();
		this.brand = brand;
		this.url = url;
		this.status = status;
	}





	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}
	static long getSerialversionuid() {
		return serialVersionUID;
	}


}
