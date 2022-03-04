package com.example.managementlibrary.scheduler;

import com.example.managementlibrary.common.Mail;
import com.example.managementlibrary.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;


@Component
public class Scheduler {

    //* "0 0 * * * *" = the top of every hour of every day.
    //* "*/10 * * * * *" = every ten seconds.
    //* "0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.
    //* "0 0 8,10 * * *" = 8 and 10 o'clock of every day.
    //* "0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30 and 10 o'clock every day.
    //* "0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
    //* "0 0 0 25 12 ?" = every Christmas Day at midnight

    // Cron expression is represented by six fields:
    // second, minute, hour, day of month, month, day(s) of week
    //(*) means match any
    //*/X means "every X"

    @Autowired
    MailService mailService;
    @Scheduled(cron = "0 56 8 * * *", zone = "Asia/Saigon")
    public void cronJobSch() throws MessagingException {
        Mail mail=new Mail();
        mail.setMailFrom("nqh60th1@gmail.com");
        mail.setMailTo("conma003@gmail.com");
        mail.setMailSubject("Thong bao");
        mail.setMailContent("Sach het han");
        mailService.sendEmail(mail);
    }
}