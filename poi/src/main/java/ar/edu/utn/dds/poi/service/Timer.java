package ar.edu.utn.dds.poi.service;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ar.edu.utn.dds.poi.constant.Constant;
import ar.edu.utn.dds.poi.domain.POI;

public class Timer implements Searcher {
	
	private POIService poiService;
	
	public Timer() {
		poiService = new POIService();
	}

	@Override
	public List<POI> search(String filter) {
		long startingTime = System.currentTimeMillis();
		List<POI> pois = poiService.search(filter);
		long endingTime = System.currentTimeMillis();
		long totalTime = endingTime - startingTime;
		System.out.println("seconds " + totalTime / 1000);
		if (totalTime > (Constant.MAX_SEARCH_TIME * 1000)) {
			EmailSender emailSender = new EmailSender("faculavag@gmail.com");
			new Thread(emailSender).start();
		}
		return pois;
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
				message.setSubject("Testing Subject");
				message.setText("Testing Text,\n\n Bye!");

				Transport.send(message);

			} catch (MessagingException e) {
				System.out.println("Could not send email to " + mail);
			}

		}
	}
}