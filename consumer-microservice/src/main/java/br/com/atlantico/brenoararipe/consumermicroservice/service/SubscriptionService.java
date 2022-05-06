package br.com.atlantico.brenoararipe.consumermicroservice.service;

import br.com.atlantico.brenoararipe.consumermicroservice.model.Subscription.Subscription;
import br.com.atlantico.brenoararipe.consumermicroservice.model.Subscription.SubscriptionBuilder;
import br.com.atlantico.brenoararipe.consumermicroservice.repository.SubscriptionRepository;
import constants.RabbitMQConstants;
import dto.SendEmailDto;
import dto.SubscriptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubscriptionService {

    /**
     * SubscriptionRepository instance.
     *
     */
    @Autowired
    SubscriptionRepository subscriptionRepository;

    /**
     * EventHistoryService instance.
     *
     */
    @Autowired
    EventHistoryService eventHistoryService;

    /**
     * RabbitmqService instance.
     *
     */
    @Autowired
    private RabbitmqService rabbitmqService;

    /**
     * Method to create a new Subscription.
     *
     * @param subscriptionDto {@link SubscriptionDto}
     * @param type {@link String}
     * @return an instance of {@link Subscription}
     */
    public Subscription registerSubscription(SubscriptionDto subscriptionDto, String type, String status) {
        Subscription subscription = SubscriptionBuilder.builder()
                .email(subscriptionDto.email)
                .statusId(status)
                .build();
        if (subscriptionRepository.findByEmail(subscriptionDto.email) != null) {
            return new Subscription();
        } else {

        subscriptionRepository.save(subscription);

        eventHistoryService.registerHistory(subscription, type);

        SendEmailDto sendEmailDto = new SendEmailDto(subscriptionDto.email, type);

        this.rabbitmqService.sendMessage(RabbitMQConstants.QUEUE_EMAIL_REGISTER, sendEmailDto);

            return subscription;
        }
    }

    /**
     * Method to update an existing Subscription.
     *
     * @param subscriptionDto {@link SubscriptionDto}
     * @param type {@link String}
     * @return an instance of {@link Subscription}
     */
    @Transactional
    public Subscription updateSubscription(SubscriptionDto subscriptionDto, String type, String status) {
        if (subscriptionRepository.findByEmail(subscriptionDto.email) != null) {
            Subscription subscription = subscriptionRepository.findByEmail(subscriptionDto.email);
            subscription.setStatusId(status);
            subscriptionRepository.save(subscription);

            eventHistoryService.registerHistory(subscription, type);

            SendEmailDto sendEmailDto = new SendEmailDto(subscriptionDto.email, type);

            this.rabbitmqService.sendMessage(RabbitMQConstants.QUEUE_EMAIL_SUBSCRIPTION_UPDATE, sendEmailDto);

            return subscription;
        }
        return new Subscription();
    }
}
