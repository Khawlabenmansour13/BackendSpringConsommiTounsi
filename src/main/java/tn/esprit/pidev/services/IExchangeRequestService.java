package tn.esprit.pidev.services;

public interface IExchangeRequestService {

	
	//  boolean checkIfEligibleForExchange(int userId, int appointmentId);



	    boolean acceptExchange(int exchangeId) throws Exception;

	    boolean rejectExchange(int exchangeId) throws Exception;

	    boolean requestExchange(int oldAppointmentId, int newAppointmentId) throws Exception;
}
