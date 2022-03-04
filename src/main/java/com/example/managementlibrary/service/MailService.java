package com.example.managementlibrary.service;

import com.example.managementlibrary.common.Mail;

import javax.mail.MessagingException;

public interface MailService
{
	void sendEmail(Mail mail) throws MessagingException;

    void sendEmailResetPassword(String email,String resetPasswordLink) throws MessagingException;
    void sendEmailVerityAccount(String email,String verityLink) throws MessagingException;
}