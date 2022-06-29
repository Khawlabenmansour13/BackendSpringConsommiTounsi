package tn.esprit.pidev.services;

import com.stripe.exception.StripeException;

public interface IPaymentService {

	public String createStripeCustomer(int idUser);
	public String retrieveStripeCustomer(String idCus);
	public String createCardForCustumorStripe(String customerId, String carta, String expMonth, String expYear, String cvc) throws StripeException;
	public void chargeCustomer(int amount);
}