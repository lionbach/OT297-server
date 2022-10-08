package com.alkemy.ong.service.impl;

import com.alkemy.ong.service.EmailService;
import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private Environment env;

    @Value("${ORGANIZATION_ID}")
    private String emailSender;

    @Value("${SENDGRID_API_KEY}")
    private String apiKey;

    @Value("${TEMPLATE_ID}")
    private String templateRegister;

    @Value("${TEMPLATE_ID_CONTACT}")
    private String templateContact;

    @Value("${alkemy.ong.email.enabled}")
    private boolean enabled;

    @Override
    public void sendEmailTo(String emailTo, Integer object) {
        if (!enabled) {
            return;
        }
        Email fromEmail = new Email(emailSender);
        Email toEmail = new Email(emailTo);
        Content content;
        String subject;
        switch (object) {
            case 1:
                content = new Content(
                        "text/html",
                        "Welcome to ONG Somos Mas!");
                subject = "WELCOME";
                break;
            case 2:
                content = new Content("text/html", "You've contacted us, we will answer you as soon as posible");
                subject = "You've contacted with ONG Somos Mas";
                break;
            default:
                content = new Content(
                        "",
                        "");
                subject = "";
                break;
        }
        setEmail(fromEmail, toEmail, content, subject);
    }

    private void setEmail(Email fromEmail, Email toEmail, Content content, String subject) {
        Mail mail = new Mail(fromEmail, subject, toEmail, content);
        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        sendEmail(mail, sg, request);
    }

    private void sendEmail(Mail mail, SendGrid sg, Request request) {
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            System.out.println("Error trying to send the email");
        }
    }
}
