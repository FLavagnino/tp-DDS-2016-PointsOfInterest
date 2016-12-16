package ar.edu.utn.dds.poi.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ar.edu.utn.dds.poi.constant.Constant;

public class EmailSender implements Runnable 
{	
	private String mail;
	private String subject;
	private String msg;
	
	private final String MAIL_SMTP_AUTH = "mail.smtp.auth";
	private final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
	private final String MAIL_SMTP_HOST = "mail.smtp.host";
	private final String MAIL_SMTP_PORT = "mail.smtp.port";
	
	public EmailSender(String mail, String subject, String msg) 
	{
		this.mail = mail;
		this.subject = subject;
		this.msg = msg;
	}
	
	public void run() 
	{
		Properties props = new Properties();
		props.put(MAIL_SMTP_AUTH, Constant.MAIL_SMTP_AUTH_VALUE);
		props.put(MAIL_SMTP_STARTTLS_ENABLE, Constant.MAIL_SMTP_STARTTLS_ENABLE_VALUE);
		props.put(MAIL_SMTP_HOST, Constant.MAIL_SMTP_HOST_VALUE);
		props.put(MAIL_SMTP_PORT, Constant.MAIL_SMTP_PORT_VALUE);
		
		Session session = Session.getInstance(props, new javax.mail.Authenticator() 
									{
										protected PasswordAuthentication getPasswordAuthentication() 
										{
											return new PasswordAuthentication(Constant.EMAIL, Constant.PASSWORD);
										}
									});

		try 
		{
			Message message = new MimeMessage(session);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
			message.setSubject(subject);//(Constant.MAIL_SUBJECT);
			message.setText(msg);//(Constant.MAIL_TEXT);

			Transport.send(message);

		} 
		catch (MessagingException e) 
		{
			System.out.println(Constant.MAIL_ERROR + mail);
		}
	}
}