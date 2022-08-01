package com.company.paydaytrade.service;

public interface EmailSender {
    void sendEmail(String toEmail, String subject, String body);
}
