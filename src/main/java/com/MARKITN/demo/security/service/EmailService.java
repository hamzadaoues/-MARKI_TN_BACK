package com.MARKITN.demo.security.service;

import com.MARKITN.demo.model.User;
import com.MARKITN.demo.security.model.Mail;
import com.MARKITN.demo.security.model.PasswordResetToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TokenService tokenService;

    //Formatting ResetToken URL with ResetToken
    public String getResetMailUrl(String appUrl, Map<String, Object> model) {
        PasswordResetToken token = (PasswordResetToken) model.get("token");
        System.out.println("here");
        System.out.println(token.getToken());

        return appUrl + "/reset-password/" + token.getToken();
    }

    public void configureMail(Map<String, Object> model) {
        String toServer = "localhost";
        String toServerPort = "4200";
        String appUrl = "http://" + toServer + ":" + toServerPort;
        String url = new String();
            url = getResetMailUrl(appUrl, model);
        model.put("url", url);
    }

    public void sendEmailWithAttachment(Mail mail, String mailContent) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(mail.getTo());
        User user = (User) mail.getModel().get("user");
        helper.setSubject("Reset Password Request MARKI.TN");
        helper.setText(mailContent, true);
        helper.addAttachment("logo.png", new ClassPathResource("logo.png"));
        javaMailSender.send(msg);

    }


    public void createResetMail(User user) throws IOException, MessagingException {


        //ResetToken Creation-Save
        PasswordResetToken token = tokenService.createRestToken(user);
        System.out.println(token.getToken());
        //Mail Creation-Save
        Mail mail = new Mail();
        mail.setTo(user.getEmail());

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);

        configureMail(model);
        mail.setModel(model);

        // true = text/html
        String message = "\n Hi Mr/Mrs "+user.getName()+"We received a request to reset your password for your MARKI.TN account.\n";
        String mailContent = message + "<br/><a href=\"" + mail.getModel().get("url").toString() + "\">Click here to set a new password!</a>";
        this.sendEmailWithAttachment(mail, mailContent);
    }

}
