package br.com.atlantico.brenoararipe.consumermicroservice.consumer;

import dto.SubscriptionDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static constants.RabbitMQConstants.QUEUE_SUBSCRIPTION;

@Component
public class SubscriptionConsumer {

    @RabbitListener(queues = QUEUE_SUBSCRIPTION)
    private void consumer(SubscriptionDto subscriptionDto) {

        System.out.println(subscriptionDto.id);
        System.out.println(subscriptionDto.status_id);
        System.out.println("-----------------------------");
    }
}
