package br.com.atlantico.brenoararipe.subsapi.controller;

import br.com.atlantico.brenoararipe.subsapi.service.RabbitmqService;
import dto.SubscriptionDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static constants.RabbitMQConstants.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class SubscriptionControllerTest {

    @Autowired
    SubscriptionController subscriptionController;

    @MockBean
    SubscriptionDto subscriptionDtoMock;

    @MockBean
    private RabbitmqService rabbitmqService;

    @Test
    @DisplayName("Should create a new subscription")
    public void register_shouldSendMessageToQueueRegisterNewSubscription() {
        // ACT
        subscriptionController.register(subscriptionDtoMock);

        // ASSERT
        verify(rabbitmqService).sendMessage(QUEUE_REGISTER, subscriptionDtoMock);
    }

    @Test
    @DisplayName("Should purchase a subscription")
    public void purchaseSubscription_shouldSendMessageToQueuePurchaseSubscription() {
        // ACT
        subscriptionController.purchaseSubscription(subscriptionDtoMock);

        // ASSERT
        verify(rabbitmqService).sendMessage(QUEUE_PURCHASE, subscriptionDtoMock);
    }

    @Test
    @DisplayName("Should cancel a subscription")
    public void cancelSubscription_shouldSendMessageToQueueCancelSubscription() {
        // ACT
        subscriptionController.cancelSubscription(subscriptionDtoMock);

        // ASSERT
        verify(rabbitmqService).sendMessage(QUEUE_CANCEL, subscriptionDtoMock);
    }

    @Test
    @DisplayName("Should recover a subscription")
    public void recoverSubscription_shouldSendMessageToQueueRecoverSubscription() {
        // ACT
        subscriptionController.restartSubscription(subscriptionDtoMock);

        // ASSERT
        verify(rabbitmqService).sendMessage(QUEUE_RECOVER, subscriptionDtoMock);
    }

    @Test
    @DisplayName("Should check health")
    public void check_shouldHeathCheck() {
        // ACT
        String check = subscriptionController.check();

        // ASSERT
        assertTrue(check.getClass().equals(String.class));
    }
}
