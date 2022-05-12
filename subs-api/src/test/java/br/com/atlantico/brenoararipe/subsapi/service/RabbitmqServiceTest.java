package br.com.atlantico.brenoararipe.subsapi.service;

import dto.SubscriptionDto;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static constants.RabbitMQConstants.QUEUE_REGISTER;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class RabbitmqServiceTest {

    @Autowired
    private RabbitmqService rabbitmqService;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @MockBean
    private SubscriptionDto subscriptionDtoMock;

    @Test
    public void sendMessage_shouldSendAnMessageToAQueue() {
        // ACT
        rabbitmqService.sendMessage(QUEUE_REGISTER, subscriptionDtoMock);

        // ASSERT
        verify(rabbitTemplate).convertAndSend(QUEUE_REGISTER, subscriptionDtoMock);
    }
}
