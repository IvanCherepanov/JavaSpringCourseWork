package com.example.demo.services.impl;

import com.example.demo.model.enumerations.MyValues;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Properties;

import static com.example.demo.model.enumerations.MyValues.*;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSenderImpl javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @SneakyThrows
    @Async
    public void sendmail(String mail, String message){
        System.out.println("Preparation to send email...");

        MimeMessage msg = javaMailSender.createMimeMessage();
        msg.setFrom(new InternetAddress(EMAILSERVER, false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));

        msg.setSubject("AnimalShop. Новый заказ." + DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                .format(LocalDateTime.now()));

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html; charset=UTF-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        msg.setContent(multipart);

        javaMailSender.send(msg);
        System.out.println("Email Send!");
    }
}
