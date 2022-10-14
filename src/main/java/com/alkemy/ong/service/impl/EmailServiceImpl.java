package com.alkemy.ong.service.impl;

import com.alkemy.ong.exception.GenericException;
import com.alkemy.ong.service.EmailService;
import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private Environment env;
    @Value("${alkemy.ong.email.templateid}")
    private String templateRegisterId;
    @Value("${alkemy.ong.email.sender}")
    private String organizationId;
    @Value("${alkemy.ong.email.apikey}")
    private String sendGridKey;
    @Value("${alkemy.ong.email.templateidcontact}")
    private String templateContactId;
    private boolean enabledMailService;


    public void sendRegisterMail(String email){
        filterEmail(email, templateRegisterId);
    }

    public void sendContactMail(String email){
        filterEmail(email, templateContactId);
    }

    private void filterEmail(String emailTo, String templateId) {
        if (!enabledMailService) {
            return;
        }
        setEmail(emailTo, templateId);
    }

    private void setEmail(String emailTo, String templateId){
        Email fromEmail = new Email(organizationId);
        Email toEmail = new Email(emailTo);
        Content content = new Content("text/html", "xxx");
        String subject = "xxx";
        Mail mail = new Mail(fromEmail, subject, toEmail, content);
        mail.setTemplateId(templateId);
        SendGrid sg = new SendGrid(sendGridKey);
        Request request = new Request();
        sendEmail(mail, sg, request);
    }

    private void sendEmail(Mail mail, SendGrid sg, Request request) {
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println("------------------  SENDGRID  ----------------------");
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
            System.out.println("----------------------------------------------------");
        } catch (IOException ex) {
            throw new GenericException(HttpStatus.INTERNAL_SERVER_ERROR, "Error trying to send the email", ex.getMessage());
        }
    }


}
