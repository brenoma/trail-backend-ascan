package br.com.atlantico.brenoararipe.emailapi.consumer;

import br.com.atlantico.brenoararipe.emailapi.service.EmailSenderService;
import dto.SendEmailDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static constants.RabbitMQConstants.*;

/**
 * This class consumes messages from RabbitMQ.
 *
 */
@Component
public class EmailConsumer {

    /**
     * EmailSenderService instance.
     *
     */
    @Autowired
    EmailSenderService emailSenderService;

    /**
     * Consumer that will consume a message from the email register queue.
     *
     * @param sendEmailDto {@link SendEmailDto}
     */
    @RabbitListener(queues = QUEUE_EMAIL_REGISTER)
    private void emailRegisterSubscriptionConsumer(SendEmailDto sendEmailDto) {
        this.emailSenderService.sendRegisterEmail(sendEmailDto.email);
    }

    /**
     * Consumer that will consume a message from the email purchase queue.
     *
     * @param sendEmailDto {@link SendEmailDto}
     */
    @RabbitListener(queues = QUEUE_EMAIL_SUBSCRIPTION_UPDATE)
    private void emailPurchaseSubscriptionConsumer(SendEmailDto sendEmailDto) {
        this.emailSenderService.sendSubscriptionEmail(sendEmailDto);
    }
}
