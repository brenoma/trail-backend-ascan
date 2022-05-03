package br.com.atlantico.brenoararipe.consumermicroservice.consumer;

import br.com.atlantico.brenoararipe.consumermicroservice.service.SubscriptionService;
import dto.SubscriptionDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static constants.RabbitMQConstants.*;
import static constants.SubscriptionStatusConstants.ACTIVE;
import static constants.SubscriptionStatusConstants.INACTIVE;

/**
 * This class consumes messages from RabbitMQ.
 *
 */
@Component
public class SubscriptionConsumer {

    /**
     * Creation constant for EventHistory types.
     *
     */
    private static final String SUBSCRIPTION_CREATE = "CREATE";

    /**
     * Purchase constant for EventHistory types.
     *
     */
    private static final String SUBSCRIPTION_PURCHASE = "PURCHASE";

    /**
     * Cancel constant for EventHistory types.
     *
     */
    private static final String SUBSCRIPTION_CANCEL = "CANCEL";

    /**
     * Recover constant for EventHistory types.
     *
     */
    private static final String SUBSCRIPTION_RECOVER = "RECOVER";

    /**
     * SubscriptionService instance.
     *
     */
    @Autowired
    SubscriptionService subscriptionService;

    /**
     * Consumer that will consume a message from the purchase queue.
     *
     * @param subscriptionDto {@link SubscriptionDto}
     */
    @RabbitListener(queues = QUEUE_PURCHASE)
    private void subscriptionPurchaseConsumer(SubscriptionDto subscriptionDto) {

        if (subscriptionDto.id != null) {
            subscriptionDto.status_id = ACTIVE;
            subscriptionService.updateSubscription(subscriptionDto, SUBSCRIPTION_PURCHASE);
        } else {
            System.out.println("Invalid ID");
        }
    }

    /**
     * Consumer that will consume a message from the cancel queue.
     *
     * @param subscriptionDto {@link SubscriptionDto}
     */
    @RabbitListener(queues = QUEUE_CANCEL)
    private void subscriptionCancelConsumer(SubscriptionDto subscriptionDto) {

        if (subscriptionDto.id != null) {
            subscriptionDto.status_id = INACTIVE;
            subscriptionService.updateSubscription(subscriptionDto, SUBSCRIPTION_CANCEL);
        } else {
            System.out.println("Invalid ID");
        }
    }

    /**
     * Consumer that will consume a message from the recover queue.
     *
     * @param subscriptionDto {@link SubscriptionDto}
     */
    @RabbitListener(queues = QUEUE_RECOVER)
    private void subscriptionRecoverConsumer(SubscriptionDto subscriptionDto) {

        if (subscriptionDto.id != null) {
            subscriptionDto.status_id = ACTIVE;
            subscriptionService.updateSubscription(subscriptionDto, SUBSCRIPTION_RECOVER);
        } else {
            System.out.println("Invalid ID");
        }
    }

    /**
     * Consumer that will consume a message from the register queue.
     *
     * @param subscriptionDto {@link SubscriptionDto}
     */
    @RabbitListener(queues = QUEUE_REGISTER)
    private void subscriptionRegisterConsumer(SubscriptionDto subscriptionDto) {

        subscriptionDto.status_id = INACTIVE;
        subscriptionService.registerSubscription(subscriptionDto, SUBSCRIPTION_CREATE);
    }
}
