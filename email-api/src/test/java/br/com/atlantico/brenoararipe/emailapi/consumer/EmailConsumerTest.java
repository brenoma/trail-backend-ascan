package br.com.atlantico.brenoararipe.emailapi.consumer;

import br.com.atlantico.brenoararipe.emailapi.service.EmailSenderService;
import dto.SendEmailDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=EmailConsumer.class, loader= AnnotationConfigContextLoader.class)
public class EmailConsumerTest {

    @Autowired
    EmailConsumer emailConsumer;

    @MockBean
    SendEmailDto sendEmailDtoMock;

    @MockBean
    EmailSenderService emailSenderServiceMock;

    @Test
    @DisplayName("Should send message to register new email")
    public void emailRegisterSubscriptionConsumer_shouldSendMessageToRegisterNewEmail() {
        emailConsumer.emailRegisterSubscriptionConsumer(sendEmailDtoMock);

        verify(emailSenderServiceMock).sendRegisterEmail(sendEmailDtoMock.email);
    }

    @Test
    @DisplayName("Should send message to subscription update")
    public void emailRegisterSubscriptionConsumer_shouldSendMessageToSubscriptionUpdate() {
        emailConsumer.emailPurchaseSubscriptionConsumer(sendEmailDtoMock);

        verify(emailSenderServiceMock).sendSubscriptionEmail(sendEmailDtoMock);
    }
}
