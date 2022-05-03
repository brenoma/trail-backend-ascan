package br.com.atlantico.brenoararipe.subsapi.connection;

import constants.RabbitMQConstants;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Component to configure RabbitMQ Queues and Exchanges.
 *
 */
@Component
public class RabbitMQConnection {

    /**
     * This holds the information about which exchange will be used.
     *
     */
    private static final String EXCHANGE_NAME = "amq.direct";

    /**
     * AmpqAdmin instance.
     *
     */
    @Autowired
    private AmqpAdmin amqpAdmin;

    /**
     * Create an instance of queue.
     * Has Other params locked to create a default type of Queues.
     *
     * @param queueName {@link String}
     * @return {@link Queue}
     */
    private Queue queue(String queueName) {
        return new Queue(queueName, true, false, false);
    }

    /**
     * Create the type of Exchange that will be used.
     *
     * @return {@link DirectExchange}
     */
    private DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    /**
     * Creates a link between a Queue and an Exchange.
     *
     * @param queue {@link Queue}
     * @param exchange {@link DirectExchange}
     * @return {@link Binding}
     */
    private Binding binding(Queue queue, DirectExchange exchange) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
    }

    /**
     * Configuration that will be performed after the injection of the class.
     *
     */
    @PostConstruct
    private void add() {
        Queue queuePurchase = this.queue(RabbitMQConstants.QUEUE_PURCHASE);
        Queue queueCancel = this.queue(RabbitMQConstants.QUEUE_CANCEL);
        Queue queueRecover = this.queue(RabbitMQConstants.QUEUE_RECOVER);
        Queue queueRegister = this.queue(RabbitMQConstants.QUEUE_REGISTER);

        DirectExchange exchange = this.directExchange();

        Binding bindPurchase = this.binding(queuePurchase, exchange);
        Binding bindCancel = this.binding(queueCancel, exchange);
        Binding bindRecover = this.binding(queueRecover, exchange);
        Binding bindRegister = this.binding(queueRegister, exchange);

        /**
         * Creation of the queues on RabbitMQ.
         *
         */
        this.amqpAdmin.declareQueue(queuePurchase);
        this.amqpAdmin.declareQueue(queueCancel);
        this.amqpAdmin.declareQueue(queueRecover);
        this.amqpAdmin.declareQueue(queueRegister);

        /**
         * Creating of the exchange on RabbitMQ.
         *
         */
        this.amqpAdmin.declareExchange(exchange);

        /**
         * Creating of the bindings between queues and exchanges.
         *
         */
        this.amqpAdmin.declareBinding(bindPurchase);
        this.amqpAdmin.declareBinding(bindCancel);
        this.amqpAdmin.declareBinding(bindRecover);
        this.amqpAdmin.declareBinding(bindRegister);
    }
}
