package tn.esprit.pidev.services;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;

import tn.esprit.pidev.entities.User;
import tn.esprit.pidev.repositories.IUserRepository;

@Service
public class PayementServiceImpl implements IPaymentService {


	
	@Autowired
	IUserRepository userRepository;
	
	@Override
	public String createStripeCustomer(int idUser) {
		Stripe.apiKey = "sk_test_51IbHP8DxH5SQBvrSKjOZFhKSaOhkUjJek6BFIAFgf0vldFGr9Z50aqp1qiH08G218ay91SZ8avMDQJsB0F9N93W7007f3xyH4h";
		User user = userRepository.findById(idUser).get();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("description", "My First Test Customer (created for API docs)");
		params.put("email", user.getAdress());
        JSONArray customerArray = new JSONArray();

		try {
		Customer customer = Customer.create(params);
		 JSONObject customerJson = new JSONObject("{}");
		 customerJson.put(" Success","account stripe was creating !! welcome");

		 customerJson.put("Email ",customer.getEmail());
		 customerJson.put("Description ",customer.getDescription());
         customerArray.put(customerJson);
   
		} catch (Exception e) {

			throw new RuntimeException(e);
		}
		return customerArray.toString();
	}
	/*@Override
	public String retrieveStripeCustomer(String idCus) {
		Stripe.apiKey = "sk_test_51ITlifLvbQLCNmpygTqUOW5h6AGcrNomswdSqY395on9wGTD287LySxr5nEgpcnPGMny1dXIm3S49iluSOgpeFaq00PDTQDP1r";
		try {
			Customer a = Customer.retrieve(idCus);
			Gson gson = new GsonBuilder().create();
			return gson.toJson(a);
		} catch (StripeException e) {
			throw new RuntimeException(e);
		}
	}
	
	*/

	@Override
	public String createCardForCustumorStripe(String customerId, String card, String expMonth, String expYear,
			String cvc) throws StripeException {
		Stripe.apiKey = "sk_test_51IbHP8DxH5SQBvrSKjOZFhKSaOhkUjJek6BFIAFgf0vldFGr9Z50aqp1qiH08G218ay91SZ8avMDQJsB0F9N93W7007f3xyH4h";
		Customer customer = Customer.retrieve(customerId);
		Map<String, Object> cardParam = new HashMap<String, Object>();
		cardParam.put("number", card);
		cardParam.put("exp_month", expMonth);
		cardParam.put("exp_year", expYear);
		cardParam.put("cvc", cvc);
		Map<String, Object> tokenParam = new HashMap<String, Object>();
		tokenParam.put("card", cardParam);

		Token token = Token.create(tokenParam);
		Map<String, Object> source = new HashMap<String, Object>();
		source.put("source", token.getId());

		customer.getSources().create(source);
		return token.getId();
	}

	@Override
	public void chargeCustomer(int amount) {
		Stripe.apiKey = "sk_test_51IbHP8DxH5SQBvrSKjOZFhKSaOhkUjJek6BFIAFgf0vldFGr9Z50aqp1qiH08G218ay91SZ8avMDQJsB0F9N93W7007f3xyH4h";
		Map<String, Object> params = new HashMap<>();
		params.put("amount", amount);
		params.put("currency", "usd");
		params.put("customer", "cus_JDphpdQFwDh5o1");

		try {
			Charge charge = Charge.create(params);
		} catch (StripeException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public String retrieveStripeCustomer(String idCus) {
		// TODO Auto-generated method stub
		return null;
	}

}