package com.timeit.Skand1s.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


@Service
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public void sendmailApprove(String toMail, String fromMail, String status) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromMail);
        message.setTo(toMail);
        message.setSubject("Vacation Request");
        message.setText("Your request has been : "+status);
        javaMailSender.send(message);
    }

    @Override
    public void sendmail(String name, String toMail, String fromMail, Date fromDate, Date toDate, String reason) throws AddressException, MessagingException, IOException {
        String pattern = " yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date1 = simpleDateFormat.format(fromDate);
        String date2 = simpleDateFormat.format(toDate);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromMail);
        message.setTo(toMail);
        message.setSubject("Vacation Request :"+ name);
        message.setText("You have a new Vacation approval request from "+name+"\n from : "+date1+ "\n To : "+ date2 + "\n Reason : "+reason);
        javaMailSender.send(message);
    }
}