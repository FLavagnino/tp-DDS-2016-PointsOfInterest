package ar.edu.utn.dds.poi.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ar.edu.utn.dds.poi.constant.Constant;
import ar.edu.utn.dds.poi.service.historical.SearchResult;

public class Audit implements Searcher {
	
	private Historical historical;
	
	public Audit() {
		historical = new Historical();
	}
	
	@Override
	public SearchResult search(String filter) {
		SearchResult searchResult = historical.search(filter);
		
		if (searchResult.getTime() > (Constant.MAX_SEARCH_TIME * 1000)) {
			//TODO completar con mail del usuario indicado
			EmailSender emailSender = new EmailSender("faculavag@gmail.com");
			new Thread(emailSender).start();
		}
		
		return searchResult;
	}
	

	private class EmailSender implements Runnable {
		
		String mail;
		
		public EmailSender(String mail) {
			this.mail = mail;
		}
		
		public void run() {
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(Constant.EMAIL, Constant.PASSWORD);
				}
			  });

			try {
				Message message = new MimeMessage(session);
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
				message.setSubject("[DDS-POI] Testing Subject");
				message.setText("Testing Text,\n\n Bye!");

				Transport.send(message);

			} catch (MessagingException e) {
				System.out.println("Could not send email to " + mail);
			}

		}
	}

}