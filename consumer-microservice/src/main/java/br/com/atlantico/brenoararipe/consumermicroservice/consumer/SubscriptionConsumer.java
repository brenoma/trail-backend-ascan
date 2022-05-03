package br.com.atlantico.brenoararipe.consumermicroservice.consumer;

import br.com.atlantico.brenoararipe.consumermicroservice.service.SubscriptionService;
import dto.SubscriptionDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static constants.RabbitMQConstants.*;
import static constants.SubscriptionStatusConstants.ACTIVE;
import static constants.SubscriptionStatusConstants.INACTIVE;

@Component
public class SubscriptionConsumer {

    private static final String SUBSCRIPTION_CREATE = "CREATE";

    private static final String SUBSCRIPTION_PURCHASE = "PURCHASE";

    private static final String SUBSCRIPTION_CANCEL = "CANCEL";

    private static final String SUBSCRIPTION_RECOVER = "RECOVER";

    @Autowired
    SubscriptionService subscriptionService;

    @RabbitListener(queues = QUEUE_PURCHASE)
    private void subscriptionPurchaseConsumer(SubscriptionDto subscriptionDto) {

        if (subscriptionDto.id != null) {
            subscriptionDto.status_id = ACTIVE;
            subscriptionService.updateSubscription(subscriptionDto, SUBSCRIPTION_PURCHASE);
        } else {
            System.out.println("Invalid ID");
        }
    }

    @RabbitListener(queues = QUEUE_CANCEL)
    private void subscriptionCancelConsumer(SubscriptionDto subscriptionDto) {

        if (subscriptionDto.id != null) {
            subscriptionDto.status_id = INACTIVE;
            subscriptionService.updateSubscription(subscriptionDto, SUBSCRIPTION_CANCEL);
        } else {
            System.out.println("Invalid ID");
        }
    }

    @RabbitListener(queues = QUEUE_RECOVER)
    private void subscriptionRecoverConsumer(SubscriptionDto subscriptionDto) {

        if (subscriptionDto.id != null) {
            subscriptionDto.status_id = ACTIVE;
            subscriptionService.updateSubscription(subscriptionDto, SUBSCRIPTION_RECOVER);
        } else {
            System.out.println("Invalid ID");
        }
    }

    @RabbitListener(queues = QUEUE_REGISTER)
    private void subscriptionRegisterConsumer(SubscriptionDto subscriptionDto) {

        subscriptionDto.status_id = INACTIVE;
        subscriptionService.registerSubscription(subscriptionDto, SUBSCRIPTION_CREATE);
    }
}
