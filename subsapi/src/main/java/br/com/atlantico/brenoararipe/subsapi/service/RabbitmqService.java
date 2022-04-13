package br.com.atlantico.brenoararipe.subsapi.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service that produce message to RabbitMQ
 */
@Service
public class RabbitmqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String queueName, Object message) {
        this.rabbitTemplate.convertAndSend(queueName, message);
    }
}
