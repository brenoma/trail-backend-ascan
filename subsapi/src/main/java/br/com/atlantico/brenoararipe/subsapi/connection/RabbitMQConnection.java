package br.com.atlantico.brenoararipe.subsapi.connection;

import constants.RabbitMQConstants;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMQConnection {
    private static final String EXCHANGE_NAME = "amq.direct";

    @Autowired
    private AmqpAdmin amqpAdmin;

    private Queue queue(String queueName) {
        return new Queue(queueName, true, false, false);
    }

    private DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    private Binding binding(Queue queue, DirectExchange exchange) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
    }

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

        // Creating the queues on RabbitMQ
        this.amqpAdmin.declareQueue(queuePurchase);
        this.amqpAdmin.declareQueue(queueCancel);
        this.amqpAdmin.declareQueue(queueRecover);
        this.amqpAdmin.declareQueue(queueRegister);

        // Creating the exchange on RabbitMQ
        this.amqpAdmin.declareExchange(exchange);

        // Creating bindings between queue and exchange
        this.amqpAdmin.declareBinding(bindPurchase);
        this.amqpAdmin.declareBinding(bindCancel);
        this.amqpAdmin.declareBinding(bindRecover);
        this.amqpAdmin.declareBinding(bindRegister);
    }
}
