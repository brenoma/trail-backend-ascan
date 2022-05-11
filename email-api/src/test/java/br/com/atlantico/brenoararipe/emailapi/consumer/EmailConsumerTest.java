package br.com.atlantico.brenoararipe.emailapi.consumer;

import br.com.atlantico.brenoararipe.emailapi.service.EmailSenderService;
import dto.SendEmailDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
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

        Mockito.verify(emailSenderServiceMock).sendRegisterEmail(sendEmailDtoMock.email);
    }

    @Test
    @DisplayName("Should send message to subscription update")
    public void emailRegisterSubscriptionConsumer_shouldSendMessageToSubscriptionUpdate() {
        emailConsumer.emailPurchaseSubscriptionConsumer(sendEmailDtoMock);

        Mockito.verify(emailSenderServiceMock).sendSubscriptionEmail(sendEmailDtoMock);
    }
}
