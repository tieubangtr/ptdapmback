package com.example.managementlibrary.service.Impl;

import com.example.managementlibrary.common.Mail;
import com.example.managementlibrary.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService
{
	@Autowired
	private JavaMailSender emailSender;

	@Override
	public void sendEmail(Mail mail) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setFrom(mail.getMailFrom());
		helper.setTo(mail.getMailTo());
		helper.setSubject(mail.getMailSubject());
		helper.setText(mail.getMailContent(),true);


		emailSender.send(message);
	}

	@Override
	public void sendEmailResetPassword(String email, String resetPasswordLink) throws MessagingException {
		Mail mail=new Mail();
		mail.setMailFrom("nqh60th1@gmail.com");
		mail.setMailTo(email);
		mail.setMailSubject("Here's the link to reset your password");
		String content = "<p>Hello,</p>"
				+ "<p>You have requested to reset your password.</p>"
				+ "<p>Click the link below to change your password:</p>"
				+ "<p><a href=\"" + resetPasswordLink + "\">Change my password</a></p>"
				+ "<br>"
				+ "<p>Ignore this email if you do remember your password, "
				+ "or you have not made the request.</p>";
		mail.setMailContent(content);
		sendEmail(mail);
	}

	@Override
	public void sendEmailVerityAccount(String email, String verityLink) throws MessagingException {
		Mail mail=new Mail();
		mail.setMailFrom("nqh60th1@gmail.com");
		mail.setMailTo(email);
		mail.setMailSubject("Please verify your registration");
		String content = "Dear [[name]],<br>"
				+ "Please click the link below to verify your registration:<br>"
				+ "<h3><a href=\""+verityLink+"\">VERIFY</a></h3>"
				+ "Thank you,<br>"
				+ "Your company name.";

		mail.setMailContent(content);
		sendEmail(mail);
	}


}
