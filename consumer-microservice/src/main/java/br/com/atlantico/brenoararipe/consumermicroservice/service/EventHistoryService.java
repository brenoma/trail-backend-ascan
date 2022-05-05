package br.com.atlantico.brenoararipe.consumermicroservice.service;

import br.com.atlantico.brenoararipe.consumermicroservice.model.EventHistory.EventHistory;
import br.com.atlantico.brenoararipe.consumermicroservice.model.EventHistory.EventHistoryBuilder;
import br.com.atlantico.brenoararipe.consumermicroservice.model.Subscription.Subscription;
import br.com.atlantico.brenoararipe.consumermicroservice.repository.EventHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventHistoryService {

    /**
     * EventHistoryRepository instance.
     *
     */
    @Autowired
    EventHistoryRepository eventHistoryRepository;

    /**
     * Method to create a new history of change in Subscriptions.
     *
     * @param subscription {@link Subscription}
     * @param type {@link String}
     * @return an instance of {@link EventHistory}
     */
    public EventHistory registerHistory(Subscription subscription, String type) {
        EventHistory eventHistory = EventHistoryBuilder
                .builder()
                .subscription(subscription)
                .type(type)
                .build();
        eventHistoryRepository.save(eventHistory);

        return eventHistory;
    }
}
