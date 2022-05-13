package br.com.atlantico.brenoararipe.emailapi.service;

import dto.SendEmailDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.mail.internet.MimeMessage;

import static constants.util.CancelSubscriptionEmailUtil.CANCEL_BODY;
import static constants.util.CancelSubscriptionEmailUtil.CANCEL_SUBJECT;
import static constants.util.PurchaseSubscriptionEmailUtil.PURCHASE_BODY;
import static constants.util.PurchaseSubscriptionEmailUtil.PURCHASE_SUBJECT;
import static constants.util.RecoverSubscriptionEmailUtil.RECOVER_BODY;
import static constants.util.RecoverSubscriptionEmailUtil.RECOVER_SUBJECT;
import static constants.util.RegisterEmailUtil.REGISTER_BODY;
import static constants.util.RegisterEmailUtil.REGISTER_SUBJECT;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=EmailSenderService.class, loader= AnnotationConfigContextLoader.class)
public class EmailSenderServiceTest {

    @Autowired
    private EmailSenderService emailSenderService;

    @SpyBean
    private EmailSenderService emailSenderServiceMock;

    @MockBean
    private SendEmailDto sendEmailDtoMock;

    @MockBean
    private JavaMailSender mailSenderMock;

    @MockBean
    private MimeMessage mimeMessageMock;

    private static final String emailMock = "mock@email.com";

    @Test
    @DisplayName("Should send a formated Register email")
    public void sendRegisterEmail_shouldSendFormatedEmail() {
        when(mailSenderMock.createMimeMessage()).thenReturn(mimeMessageMock);

        emailSenderService.sendRegisterEmail(emailMock);

        verify(emailSenderServiceMock).formatEmailToHTML(REGISTER_BODY, REGISTER_SUBJECT, emailMock);
    }

    @Test
    @DisplayName("Should send a formated Purchase email")
    public void sendSubscriptionEmail_shouldSendPurchaseFormatedEmail() {
        when(mailSenderMock.createMimeMessage()).thenReturn(mimeMessageMock);

        sendEmailDtoMock.email = emailMock;
        sendEmailDtoMock.type = "PURCHASE";

        emailSenderService.sendSubscriptionEmail(sendEmailDtoMock);

        verify(emailSenderServiceMock).formatEmailToHTML(PURCHASE_BODY, PURCHASE_SUBJECT, sendEmailDtoMock.email);
    }

    @Test
    @DisplayName("Should send a formated Cancel email")
    public void sendSubscriptionEmail_shouldSendCancelFormatedEmail() {
        when(mailSenderMock.createMimeMessage()).thenReturn(mimeMessageMock);

        sendEmailDtoMock.email = emailMock;
        sendEmailDtoMock.type = "CANCEL";

        emailSenderService.sendSubscriptionEmail(sendEmailDtoMock);

        verify(emailSenderServiceMock).formatEmailToHTML(CANCEL_BODY, CANCEL_SUBJECT, sendEmailDtoMock.email);
    }

    @Test
    @DisplayName("Should send a formated Recover email")
    public void sendSubscriptionEmail_shouldSendRecoverFormatedEmail() {
        when(mailSenderMock.createMimeMessage()).thenReturn(mimeMessageMock);

        sendEmailDtoMock.email = emailMock;
        sendEmailDtoMock.type = "RECOVER";

        emailSenderService.sendSubscriptionEmail(sendEmailDtoMock);

        verify(emailSenderServiceMock).formatEmailToHTML(RECOVER_BODY, RECOVER_SUBJECT, sendEmailDtoMock.email);
    }

//    @Test
    @DisplayName("Should catch and message exception when formatting and invalid email")
    public void formatEmailToHTML_shouldCatchMessagingException() {
//        Mockito.when(emailSenderServiceMock.formatEmailToHTML("a", "a", "a")).thenThrow(MessagingException.class);
        Mockito.doThrow(MailParseException.class).when(emailSenderServiceMock).formatEmailToHTML("a", "a", "a");

        emailSenderService.formatEmailToHTML("a", "a", "a");


    }
}
