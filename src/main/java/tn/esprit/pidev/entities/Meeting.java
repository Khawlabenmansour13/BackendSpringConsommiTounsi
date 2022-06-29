package tn.esprit.pidev.entities;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;





@Entity
@Table(name="meeting")
public class Meeting implements Serializable{
	private static final long serialVersionUID = 1L;
	  @Id
		@GeneratedValue(strategy= GenerationType.IDENTITY)
		@Column(name= "id")
		private int idMeeting;
		
	    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
		private LocalDate startDate;

		private LocalTime time;
	    
		private LocalDateTime createdAt;
	    
		@Column(name = "canceled_at")
		private LocalDateTime canceledAt;
	@OneToOne    
	@JsonIgnore

	@JoinColumn(name = "id_canceler")
	private User canceler;
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private AppointmentStatus status;

	@Column(name= "typeMeeting")
	private String typeMeeting;
	@Column(name= "description")
	private String description;
 

	@JsonIgnore

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User users;
	
	
    @ManyToOne
    @JoinColumn(name = "id_event")
    private Event events;
    
	public Meeting() {
		super();
	}

	public Meeting(LocalDate startDate, LocalTime time, LocalDateTime createdAt, LocalDateTime canceledAt, User canceler,
			AppointmentStatus status, String typeMeeting, String description, User users) {
		super();
		this.startDate = startDate;
		this.time = time;
		this.createdAt = createdAt;
		this.canceledAt = canceledAt;
		this.canceler = canceler;
		this.status = status;
		this.typeMeeting = typeMeeting;
		this.description = description;
		this.users = users;
	}

	public Meeting(int idMeeting, LocalDate startDate, LocalTime time, LocalDateTime createdAt, LocalDateTime canceledAt,
			User canceler, AppointmentStatus status, String typeMeeting, String description, User users) {
		super();
		this.idMeeting = idMeeting;
		this.startDate = startDate;
		this.time = time;
		this.createdAt = createdAt;
		this.canceledAt = canceledAt;
		this.canceler = canceler;
		this.status = status;
		this.typeMeeting = typeMeeting;
		this.description = description;
		this.users = users;
	}

	public int getIdMeeting() {
		return idMeeting;
	}

	public void setIdMeeting(int idMeeting) {
		this.idMeeting = idMeeting;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getCanceledAt() {
		return canceledAt;
	}

	public void setCanceledAt(LocalDateTime canceledAt) {
		this.canceledAt = canceledAt;
	}

	public User getCanceler() {
		return canceler;
	}

	public void setCanceler(User canceler) {
		this.canceler = canceler;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}

	public String getTypeMeeting() {
		return typeMeeting;
	}

	public void setTypeMeeting(String typeMeeting) {
		this.typeMeeting = typeMeeting;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
	
	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public Event getEvents() {
		return events;
	}

	public void setEvents(Event events) {
		this.events = events;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Meeting [idMeeting=" + idMeeting + ", startDate=" + startDate + ", time=" + time + ", createdAt="
				+ createdAt + ", canceledAt=" + canceledAt + ", canceler=" + canceler + ", status=" + status
				+ ", typeMeeting=" + typeMeeting + ", description=" + description + ", users=" + users + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((canceledAt == null) ? 0 : canceledAt.hashCode());
		result = prime * result + ((canceler == null) ? 0 : canceler.hashCode());
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((events == null) ? 0 : events.hashCode());
		result = prime * result + idMeeting;
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((typeMeeting == null) ? 0 : typeMeeting.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Meeting other = (Meeting) obj;
		if (canceledAt == null) {
			if (other.canceledAt != null)
				return false;
		} else if (!canceledAt.equals(other.canceledAt))
			return false;
		if (canceler == null) {
			if (other.canceler != null)
				return false;
		} else if (!canceler.equals(other.canceler))
			return false;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (events == null) {
			if (other.events != null)
				return false;
		} else if (!events.equals(other.events))
			return false;
		if (idMeeting != other.idMeeting)
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (status != other.status)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (typeMeeting == null) {
			if (other.typeMeeting != null)
				return false;
		} else if (!typeMeeting.equals(other.typeMeeting))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}

	

	
	
}
