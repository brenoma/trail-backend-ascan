package br.com.atlantico.brenoararipe.consumermicroservice.consumer;

import br.com.atlantico.brenoararipe.consumermicroservice.service.SubscriptionService;
import dto.SubscriptionDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static constants.RabbitMQConstants.QUEUE_REGISTER;
import static constants.RabbitMQConstants.QUEUE_SUBSCRIPTION;

@Component
public class SubscriptionConsumer {

    @Autowired
    SubscriptionService subscriptionService;

    @RabbitListener(queues = QUEUE_SUBSCRIPTION)
    private void subscriptionConsumer(SubscriptionDto subscriptionDto) {

        if (subscriptionDto.id != null) {
            subscriptionService.updateSubscription(subscriptionDto);
        } else {
            System.out.println("Invalid ID");
        }
    }

    @RabbitListener(queues = QUEUE_REGISTER)
    private void registerConsumer(SubscriptionDto subscriptionDto) {

        subscriptionService.registerSubscription(subscriptionDto);
    }
}
