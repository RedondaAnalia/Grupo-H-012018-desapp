package tests.mail;

import org.junit.Test;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailTest {
/*
    @Test
    public void sendMail(){

        // Set up the SMTP server.
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", "smtp.myisp.com");
        Session session = Session.getDefaultInstance(props, null);

        // Construct the message
        String to = "gil.maricruz@gmail.com";
        String from = "gil.maricruz@gmail.com";
        String subject = "Prueba de envío de mail Carpnd (Pick and Drive)";
        Message msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(from));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject(subject);
            msg.setText("Cuerpo del mail");

            // Send the message.
            Transport.send(msg);
        } catch (MessagingException e) {
            // Error.
        }
    }
    */
}
