package br.com.atlantico.brenoararipe.emailapi.service;

import dto.SendEmailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static br.com.atlantico.brenoararipe.emailapi.util.CancelSubscriptionEmailUtil.CANCEL_BODY;
import static br.com.atlantico.brenoararipe.emailapi.util.CancelSubscriptionEmailUtil.CANCEL_SUBJECT;
import static br.com.atlantico.brenoararipe.emailapi.util.PurchaseSubscriptionEmailUtil.PURCHASE_BODY;
import static br.com.atlantico.brenoararipe.emailapi.util.PurchaseSubscriptionEmailUtil.PURCHASE_SUBJECT;
import static br.com.atlantico.brenoararipe.emailapi.util.RecoverSubscriptionEmailUtil.RECOVER_BODY;
import static br.com.atlantico.brenoararipe.emailapi.util.RecoverSubscriptionEmailUtil.RECOVER_SUBJECT;
import static br.com.atlantico.brenoararipe.emailapi.util.RegisterEmailUtil.*;

@Service
public class EmailSenderService {

    /**
     * JavaMailSender instance.
     *
     */
    @Autowired
    private JavaMailSender mailSender;

    /**
     * Method to send an email on successful registration.
     *
     * @param toEmail {@link String}
     */
    public void sendRegisterEmail(String toEmail) {
        formatEmailToHTML(REGISTER_BODY, REGISTER_SUBJECT, toEmail);
    }

    /**
     * Method to send an email on subscription purchase.
     *
     * @param sendEmailDto {@link SendEmailDto}
     */
    public void sendSubscriptionEmail(SendEmailDto sendEmailDto) {
        switch (sendEmailDto.type) {
            case "PURCHASE": formatEmailToHTML(PURCHASE_BODY, PURCHASE_SUBJECT, sendEmailDto.email); break;
            case "CANCEL": formatEmailToHTML(CANCEL_BODY, CANCEL_SUBJECT, sendEmailDto.email); break;
            case "RECOVER": formatEmailToHTML(RECOVER_BODY, RECOVER_SUBJECT, sendEmailDto.email); break;
        }
    }

    /**
     * Method to build an Email message.
     *
     * @param body {@link String}
     * @param subject {@link String}
     * @param toEmail {@link String}
     */
    private void formatEmailToHTML(String body, String subject, String toEmail) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");
            message.setContent(body, "text/html");
            helper.setFrom("subscription.api.inc@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject(subject);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new MailParseException(e);
        }
    }
}
