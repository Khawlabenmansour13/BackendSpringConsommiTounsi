package tn.esprit.pidev.entities;
import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="feedback")
public class Feedback implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "id")
	private int idFeedback;
	
	
	@Column(name= "title")
	private String title;
	
	
	@Column(name= "CreatedAt")
	private LocalDateTime createdAt;
	
	@Column(name = "updatedAt")
    private LocalDateTime updatedAt;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name= "id_meeting")
	private Meeting meeting;
	
		
	public Feedback() {
		super();
	}

	public int getIdFeedback() {
		return idFeedback;
	}

	public void setIdFeedback(int idFeedback) {
		this.idFeedback = idFeedback;
	}


	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
  
	@Override
	public String toString() {
		return "Feedback [idFeedback=" + idFeedback + ", title=" + title + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", meeting=" + meeting + "]";
	}

	
	

	
	
}
