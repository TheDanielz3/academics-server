package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Stateless
public class EmailBean {

    @Resource(name = "java:/jboss/mail/fakeSMTP")
    private Session mailSession;

    private static final Logger logger = Logger.getLogger("EmailBean.logger");

    public void send(String to, String subject, String text) throws MessagingException {
        Message message = new MimeMessage(mailSession);
        try {

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to, false));

            // Set the message's subject
            message.setSubject(subject);

            message.setText(text);

            Date timeStamp = new Date();
            message.setSentDate(timeStamp);


            // Send the message
            Transport.send(message);

        }catch (MessagingException e){
            throw  e;
        }

    }

}
