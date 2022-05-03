package br.com.atlantico.brenoararipe.consumermicroservice.service;

import br.com.atlantico.brenoararipe.consumermicroservice.model.Subscription;
import br.com.atlantico.brenoararipe.consumermicroservice.repository.SubscriptionRepository;
import dto.SubscriptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    EventHistoryService eventHistoryService;

    public void registerSubscription(SubscriptionDto subscriptionDto) {
        Subscription subscription = new Subscription(subscriptionDto.email, subscriptionDto.status_id);
        subscriptionRepository.save(subscription);
    }

    @Transactional
    public void updateSubscription(SubscriptionDto subscriptionDto) {
        if (subscriptionDto.id != null) {
            Subscription subscription = subscriptionRepository.getById(subscriptionDto.id);
            subscription.setStatusId(subscriptionDto.status_id);
            subscriptionRepository.save(subscription);

            eventHistoryService.registerHistory("PURCHASE", subscription);
        }
    }
}
