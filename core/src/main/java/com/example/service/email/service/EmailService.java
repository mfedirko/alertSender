package com.example.service.email.service;


import model.entity.api.Email;

public interface EmailService {
    boolean sendEmail(Email email);
}
