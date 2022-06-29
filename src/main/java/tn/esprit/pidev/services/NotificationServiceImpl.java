package tn.esprit.pidev.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pidev.entities.Event;
import tn.esprit.pidev.entities.Notification;
import tn.esprit.pidev.entities.User;
import tn.esprit.pidev.repositories.INotificationRepository;
import tn.esprit.pidev.repositories.IUserRepository;
@Service
public class NotificationServiceImpl implements INotificationService {
	@Autowired
	IUserRepository iUserRepository;
	@Autowired
	EventServiceImpl eventServiceImpl;
	
	@Autowired
	INotificationRepository iNotificationRepository;
	/*********************************Admin**************************************/
    /*******************************send notification to user *******************************************/
	@Override
	public void notiflUser(Event ev) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		List<User> allUsers = (List<User>) iUserRepository.findAll();
		for(User u : allUsers) {
			Notification n = new Notification();
			n.setEvent(ev);
			n.setUser(u);
			n.setDescription("Hello "+u.getLastname()+" "+u.getFirstname()+",Welcome to donate to event"+ 
					  ev.getTitle()+" for "+ev.getDescription()+".Thank you.");
			n.setDate(date);
			n.setStatus("Not Seen");
			iNotificationRepository.save(n);
		}
	}
	

}
