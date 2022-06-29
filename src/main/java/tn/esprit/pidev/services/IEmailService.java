package tn.esprit.pidev.services;


import org.springframework.mail.SimpleMailMessage;
public interface IEmailService {
	public void sendEmail(SimpleMailMessage email);
}