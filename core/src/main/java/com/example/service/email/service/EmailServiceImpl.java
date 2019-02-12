package com.example.service.email.service;


import integration.email.EmailClient;
import model.entity.api.Email;

public class EmailServiceImpl implements EmailService {

    private EmailClient emailClient;

    public EmailServiceImpl(EmailClient emailClient){
        this.emailClient = emailClient;
    }


    @Override
    public boolean sendEmail(Email email){
        System.out.println("Sending email");
        return emailClient.sendEmail(email);
    }


}
