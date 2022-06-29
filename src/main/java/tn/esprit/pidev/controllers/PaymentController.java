package tn.esprit.pidev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;

import tn.esprit.pidev.services.IPaymentService;


@RestController
public class PaymentController {

	@Autowired
	IPaymentService paymentService;
	
	//Create a stripe customer
	@PostMapping(value="/AddCustomer/{idUser}")
    public String createCustomer(@PathVariable("idUser")int idUser)  {
           return    paymentService.createStripeCustomer(idUser);
    }
	
	@GetMapping("/retrieveCustomer/{idCus}")
	public String retrieveCustomer(@PathVariable("idCus")String idCus) {
		return paymentService.retrieveStripeCustomer(idCus);
	}
	//http://localhost:8081/SpringMVC/servlet/createCardCustumorStripe/cus_J7qxNbH4ptpVau/5105105105105100/02/2022/333
	@PostMapping(value="/createCardCustumorStripe/{customerId}/{card}/{expMonth}/{expYear}/{cvc}")
	@ResponseBody
    public String createCardCustumorStripe(@PathVariable("customerId")String customerId,@PathVariable("card")String card,@PathVariable("expMonth")String expMonth,@PathVariable("expYear")String expYear,@PathVariable("cvc")String cvc) throws StripeException  {
		return paymentService.createCardForCustumorStripe(customerId, card, expMonth, expYear, cvc);
    }
	
	@PostMapping(value="/chargeCustomer/{amount}")
	public void chargeCustomer(@PathVariable("amount")int amount) {
		paymentService.chargeCustomer(amount);
	}
		
}