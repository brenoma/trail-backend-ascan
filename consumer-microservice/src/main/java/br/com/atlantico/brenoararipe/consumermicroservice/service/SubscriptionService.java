package br.com.atlantico.brenoararipe.consumermicroservice.service;

import br.com.atlantico.brenoararipe.consumermicroservice.model.Subscription;
import br.com.atlantico.brenoararipe.consumermicroservice.repository.SubscriptionRepository;
import dto.SubscriptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    public void save(SubscriptionDto subscriptionDto) {
        Subscription subscription = new Subscription(subscriptionDto.email, subscriptionDto.status_id);
        Subscription responseSub = subscriptionRepository.save(subscription);
        List<Subscription> teste1 = subscriptionRepository.findAll();
    }

    public void updateSubscription(SubscriptionDto subscriptionDto) {
        Subscription subscription = subscriptionRepository.getById(subscriptionDto.id);
        System.out.println(subscription);
    }
}
