package com.hakim.datauploder.service;

import com.hakim.datauploder.pojo.MailStructure;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    @Value("${spring.mail.username}")
    public String fromMail;
    private final JavaMailSender mailSender;
    public void send(String toMail, MailStructure mail){
        var simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toMail);
        simpleMailMessage.setFrom(fromMail);
        simpleMailMessage.setSubject(mail.subject());
        simpleMailMessage.setText(mail.mail());

        mailSender.send(simpleMailMessage);
    }
}
