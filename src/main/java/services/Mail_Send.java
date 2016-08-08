package services;

import incredicorpserver.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * Created by olivier on 18/01/2016.
 */

@Service("sendMail")
public class Mail_Send {

    @Autowired
    MailSender sendUserMail;

    public void sendMailToUser(User user) {
        SimpleMailMessage mailToSend = new SimpleMailMessage();
        mailToSend.setFrom("oneumayer@hotmail.fr");
        mailToSend.setTo(user.getUserMail());
        mailToSend.setSubject("Incredicorp: Account information");
        mailToSend.setText("Login = "+user.getUserName()+" and Password = "+user.getUserPassword());
        sendUserMail.send(mailToSend);
    }
}

