package br.com.atlantico.brenoararipe.consumermicroservice.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service that produce messages to RabbitMQ.
 *
 */
@Service
public class RabbitmqService {

    /**
     * RabbitTemplate instance.
     *
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * Method to produce a message to the queue.
     *
     * @param queueName {@link String}
     * @param message {@link Object}
     */
    public void sendMessage(String queueName, Object message) {
        this.rabbitTemplate.convertAndSend(queueName, message);
    }
}
