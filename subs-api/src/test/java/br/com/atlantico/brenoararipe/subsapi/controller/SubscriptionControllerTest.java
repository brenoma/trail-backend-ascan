package br.com.atlantico.brenoararipe.subsapi.controller;

import br.com.atlantico.brenoararipe.subsapi.service.RabbitmqService;
import dto.SubscriptionDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static constants.RabbitMQConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
//@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes=SubscriptionController.class, loader= AnnotationConfigContextLoader.class)
public class SubscriptionControllerTest {

    @Autowired
//    @InjectMocks
    SubscriptionController subscriptionController;

    @MockBean
    SubscriptionDto subscriptionDtoMock;

    @MockBean
    private RabbitmqService rabbitmqService;

    @Test
    public void register_shouldSendMessageToQueueRegisterNewSubscription() {
        // ACT
        subscriptionController.register(subscriptionDtoMock);

        // ASSERT
        verify(rabbitmqService).sendMessage(QUEUE_REGISTER, subscriptionDtoMock);
    }

    @Test
    public void purchaseSubscription_shouldSendMessageToQueuePurchaseSubscription() {
        // ACT
        subscriptionController.purchaseSubscription(subscriptionDtoMock);

        // ASSERT
        verify(rabbitmqService).sendMessage(QUEUE_PURCHASE, subscriptionDtoMock);
    }

    @Test
    public void cancelSubscription_shouldSendMessageToQueueCancelSubscription() {
        // ACT
        subscriptionController.cancelSubscription(subscriptionDtoMock);

        // ASSERT
        verify(rabbitmqService).sendMessage(QUEUE_CANCEL, subscriptionDtoMock);
    }

    @Test
    public void recoverSubscription_shouldSendMessageToQueueRecoverSubscription() {
        // ACT
        subscriptionController.restartSubscription(subscriptionDtoMock);

        // ASSERT
        verify(rabbitmqService).sendMessage(QUEUE_RECOVER, subscriptionDtoMock);
    }

    @Test
    public void check_shouldHeathCheck() {
        // ACT
        String check = subscriptionController.check();

        // ASSERT

        assertEquals(String.class, check.getClass());
    }
}
