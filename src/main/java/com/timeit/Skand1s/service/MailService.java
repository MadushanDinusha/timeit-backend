package com.timeit.Skand1s.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;
import java.util.Date;

public interface MailService {
    void sendmail(String name, String toMail, String fromMail, Date fromDate, Date toDate, String reason) throws
            AddressException, MessagingException, IOException;
    void sendmailApprove(String toMail, String fromMail, String status);
}