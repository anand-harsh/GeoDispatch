package com.geodispatch.app.services.impl;

import com.geodispatch.app.services.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailSenderServiceImpl implements EmailSenderService {

    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(String toEmail, String subject, String body) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);

            log.info("Email sent successfully to {}", toEmail);

        } catch (Exception ex) {
            log.error("Failed to send email to {}", toEmail, ex);
        }
    }

    @Override
    public void sendEmail(String[] recipients, String subject, String body) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();

            // Primary recipient (required by some SMTP servers)
            message.setTo("noreply@geodispatch.com");

            message.setBcc(recipients);

            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);

            log.info("Bulk email sent successfully.");

        } catch (Exception ex) {
            log.error("Failed to send bulk email.", ex);
        }
    }
}