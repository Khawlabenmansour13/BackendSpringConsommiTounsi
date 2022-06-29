package tn.esprit.pidev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pidev.entities.AppointmentStatus;
import tn.esprit.pidev.entities.ExchangeRequest;
import tn.esprit.pidev.entities.ExchangeStatus;
import tn.esprit.pidev.entities.Meeting;
import tn.esprit.pidev.entities.Roletype;
import tn.esprit.pidev.entities.User;
import tn.esprit.pidev.repositories.IExchangeRepository;
import tn.esprit.pidev.repositories.IMeetingRepository;

@Service
public class ExchangeRequestServiceImpl    implements  IExchangeRequestService {

	
	@Autowired
	private IExchangeRepository iExchangeRepository;
	
	@Autowired
	private IMeetingRepository iMeetingRepository;
	
	//Accepter la permutation 
	@Override
	public boolean acceptExchange(int exchangeId) throws Exception {
		ExchangeRequest exchangeRequest = iExchangeRepository.getOne(exchangeId);
	        User u = new User();
	        //
	        
	        
	       // if(u.getRole().getType().equals
	        //		(Roletype.SPONSOR) || u.getRole().getType().equals(Roletype.MANAGEREVENT)) {

	            //
	            Meeting requestor = exchangeRequest.getRequestor();
	            Meeting requested = exchangeRequest.getRequested();
	            User user = requestor.getUsers();
	            requestor.setStatus(AppointmentStatus.SCHEDULED);
	            exchangeRequest.setStatus(ExchangeStatus.ACCEPTED);
	            requestor.setUsers(requested.getUsers());
	            requested.setUsers(user);
	            iExchangeRepository.save(exchangeRequest);
	            iMeetingRepository.save(requested);
	            iMeetingRepository.save(requestor);
	     
	            return true;
		
	        }

	//Refusé la permutation à  cause de raison de non disponisbilité
	    @Override
	    public boolean rejectExchange(int exchangeId) throws Exception {
	       // User u = userConnected();
	        User user = new User();
	        //		if(Roletype.SPONSOR) || u.getRole().getType().equals(Roletype.MANAGEREVENT)) {

	        ExchangeRequest exchangeRequest = iExchangeRepository.getOne(exchangeId);
	        Meeting requestor = exchangeRequest.getRequestor();
	        exchangeRequest.setStatus(ExchangeStatus.REJECTED);
	        requestor.setStatus(AppointmentStatus.SCHEDULED);
	        iExchangeRepository.save(exchangeRequest);
	        iMeetingRepository.save(requestor);
	        
	        return true;
	    }
	    //Demande de permuter le rendez vous par un autre 
		@Override
		public boolean requestExchange(int oldAppointmentId, int newAppointmentId) throws Exception {
			
			//dUser u = userConnected();
			User u = new User();
			//if(u.getRole().getRoleType().equals(RoleType.KindergardenDirector)) {
			    Meeting oldAppointment = iMeetingRepository.findById(oldAppointmentId).get();
			    Meeting newAppointment = iMeetingRepository.findById(newAppointmentId).get();
	            oldAppointment.setStatus(AppointmentStatus.EXCHANGE_REQUESTED);
	            iMeetingRepository.save(oldAppointment);
	            ExchangeRequest exchangeRequest = new ExchangeRequest(oldAppointment, newAppointment, ExchangeStatus.PENDING);
	            iExchangeRepository.save(exchangeRequest);
	           
	            return true;
	        
			}
			
	   /* @Override
	    public boolean checkIfEligibleForExchange(int userId, int appointmentId) {
	    	
	        Meeting appointment = iMeetingRepository.findById(appointmentId).get();

	        return appointment.getStartDate().plusDays(1).isAfter(LocalDate.now()) && appointment.getStatus().equals(AppointmentStatus.SCHEDULED) && appointment.getUsers().getIdUser() == userId;
	    }
	    */

	   
	  /*  @Override
		public User userConnected() throws Exception{
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return ((UserDetailsImpl) principal).getUser();
		}
	*/

	}