package br.com.atlantico.brenoararipe.consumermicroservice.service;

import dto.SubscriptionDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static constants.RabbitMQConstants.QUEUE_REGISTER;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=RabbitmqService.class, loader= AnnotationConfigContextLoader.class)
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
