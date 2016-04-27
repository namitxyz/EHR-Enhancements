package org.zeus.HealthEnhancements.Services;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class NotificationService
{
   public static boolean Notify(String from, String to, String subject, String body)
   {    
      boolean bsent = false;
      // Assuming we are sending email from localhost
      String host = "localhost";

      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      properties.setProperty("mail.smtp.host", host);

      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties);

      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

         // Set Subject: header field
         message.setSubject(subject);

         // Now set the actual message
         message.setText(body);

         // Send message
         Transport.send(message);

         System.out.println("Sent message successfully....");

         bsent = true;
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
      return bsent;
   }
}