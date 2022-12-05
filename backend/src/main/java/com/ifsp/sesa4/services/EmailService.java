package com.ifsp.sesa4.services;

import com.ifsp.sesa4.DTOs.EmailRequestDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail,
                                String subject,
                                String body
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail Send...");

    }

    public void sendEmail(String toEmail,
                                String subject,
                                String body
    ) throws MessagingException {
        MimeMessage message = this.mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("");
        helper.setTo(toEmail);
        helper.setText(body, true);
        helper.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail Send...");

    }

    public void sendPersonalizedEmail(EmailRequestDTO emailRequestDTO) throws MessagingException {
        MimeMessage message = this.mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("");
        helper.setTo(emailRequestDTO.getToEmail());
        helper.setText(emailRequestDTO.getBody(), false);
        helper.setSubject(emailRequestDTO.getSubject());
        mailSender.send(message);
        System.out.println("Mail Send...");

    }

}
