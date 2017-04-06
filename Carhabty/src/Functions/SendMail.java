/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Functions;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ASUS
 */
public class SendMail {
    
    private String smtpHost = "localhost";

    public SendMail(String mail,String output)
            throws MessagingException {
        boolean debug = false;
 
   

    
    
        //Set the host smtp address
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.user", "mehdi.abidi@esprit.tn");
        props.put("mail.smtp.password", "flexible1993");
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        // create some properties and get the default Session
        Session session = Session.getDefaultInstance(props, null);
        session.setDebug(debug);

        // create a message
        Message msg = new MimeMessage(session);

        // set the from and to address
        InternetAddress addressFrom = new InternetAddress("mehdi.abidi@esprit.tn");

        InternetAddress addressTo = new InternetAddress(mail);

        //addressTo = new InternetAddress(mail);

        msg.setRecipient(Message.RecipientType.TO, addressTo);
        // Setting the Subject and Content Type
        msg.setFrom(addressFrom);

        msg.setSubject("Carhabty Events");
        msg.setText(output);
        Transport t = session.getTransport("smtp");
        t.connect("smtp.gmail.com", "mehdi.abidi@esprit.tn", "flexible1993");
        t.sendMessage(msg, msg.getAllRecipients());
        t.close();
        System.out.println("sent");
    }
}
