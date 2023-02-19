package Methods;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
//this class has a method to send an email
public class Email {
	public static void send(String receiver, String subject) throws IOException {
		String to = receiver;
		String from = "uarmclient@gmail.com";
		String host = "smtp.gmail.com";
		String port = "465";
		String emailName = "From the UarmClient: " + subject;

		Properties prop = new Properties();
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.port", port);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.socketFactory.port", port);
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		final String username = from;
		final String password = "gmdg uieu mjfs shps";

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(emailName);
			Multipart emailContent = new MimeMultipart();
			MimeBodyPart body = new MimeBodyPart();
			body.attachFile("StepsList.txt");
			MimeBodyPart body1 = new MimeBodyPart();
			body1.attachFile("Exercises.txt");
			MimeBodyPart body2 = new MimeBodyPart();
			body2.attachFile("Log.txt");
			emailContent.addBodyPart(body);
			emailContent.addBodyPart(body1);
			emailContent.addBodyPart(body2);
			message.setContent(emailContent);
			Transport.send(message);
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}
