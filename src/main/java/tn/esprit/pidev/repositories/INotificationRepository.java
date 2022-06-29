package tn.esprit.pidev.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.pidev.entities.Notification;

@Repository
public interface INotificationRepository extends CrudRepository<Notification,Integer>  {

}
