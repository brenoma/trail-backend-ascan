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
    protected void subscriptionPurchaseConsumer(SubscriptionDto subscriptionDto) {
            subscriptionService.updateSubscription(subscriptionDto, SUBSCRIPTION_PURCHASE, ACTIVE);
    }

    /**
     * Consumer that will consume a message from the cancel queue.
     *
     * @param subscriptionDto {@link SubscriptionDto}
     */
    @RabbitListener(queues = QUEUE_CANCEL)
    protected void subscriptionCancelConsumer(SubscriptionDto subscriptionDto) {
            subscriptionService.updateSubscription(subscriptionDto, SUBSCRIPTION_CANCEL, INACTIVE);
    }

    /**
     * Consumer that will consume a message from the recover queue.
     *
     * @param subscriptionDto {@link SubscriptionDto}
     */
    @RabbitListener(queues = QUEUE_RECOVER)
    protected void subscriptionRecoverConsumer(SubscriptionDto subscriptionDto) {
            subscriptionService.updateSubscription(subscriptionDto, SUBSCRIPTION_RECOVER, ACTIVE);
    }

    /**
     * Consumer that will consume a message from the register queue.
     *
     * @param subscriptionDto {@link SubscriptionDto}
     */
    @RabbitListener(queues = QUEUE_REGISTER)
    protected void subscriptionRegisterConsumer(SubscriptionDto subscriptionDto) {
        subscriptionService.registerSubscription(subscriptionDto, SUBSCRIPTION_CREATE, INACTIVE);
    }
}
