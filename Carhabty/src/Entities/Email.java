/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 *
 * @author othma
 */

public class Email {

    public void sendEmail(String adress, String subject, String message) throws MessagingException {
        String from = "aminegarci100@gmail.com";
        String password = "aaaa";
        String[] to = {adress};
        String host = "smtp.gmail.com";

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.user", from);
        prop.put("mail.smtp.password", password);

        Session session = Session.getDefaultInstance(prop);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        InternetAddress[] toadress = new InternetAddress[to.length];
        for (int i = 0; i < to.length; i++) {
            toadress[i] = new InternetAddress(to[i]);
        }
        for (int i = 0; i < toadress.length; i++) {
            msg.setRecipient(Message.RecipientType.TO, toadress[i]);
        }
        msg.setSubject(subject);
        

        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(message);

        Multipart multipart = new MimeMultipart();
//
        multipart.addBodyPart(messageBodyPart);
//
//        messageBodyPart = new MimeBodyPart();
//        String filename = message;
//        DataSource source = new FileDataSource(filename);
//        messageBodyPart.setDataHandler(new DataHandler(source));
//        messageBodyPart.setFileName(filename);
//        multipart.addBodyPart(messageBodyPart);
//
//        // Send the complete message parts
        msg.setContent(multipart);

        Transport t = session.getTransport("smtp");
        t.connect(host, from, password);
        t.sendMessage(msg, msg.getAllRecipients());
        t.close();

    }


}