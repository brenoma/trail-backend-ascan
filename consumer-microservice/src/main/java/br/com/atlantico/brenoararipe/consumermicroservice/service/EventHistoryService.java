package br.com.atlantico.brenoararipe.consumermicroservice.service;

import br.com.atlantico.brenoararipe.consumermicroservice.model.EventHistory;
import br.com.atlantico.brenoararipe.consumermicroservice.model.Subscription;
import br.com.atlantico.brenoararipe.consumermicroservice.repository.EventHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventHistoryService {

    @Autowired
    EventHistoryRepository eventHistoryRepository;

    public void registerHistory(String type, Subscription subscription) {
        EventHistory eventHistory = new EventHistory(type, subscription);
        eventHistoryRepository.save(eventHistory);
    }
}
