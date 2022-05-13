package br.com.atlantico.brenoararipe.consumermicroservice.service;

import br.com.atlantico.brenoararipe.consumermicroservice.model.EventHistory.EventHistory;
import br.com.atlantico.brenoararipe.consumermicroservice.model.Subscription.Subscription;
import br.com.atlantico.brenoararipe.consumermicroservice.repository.EventHistoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=EventHistoryService.class, loader= AnnotationConfigContextLoader.class)
public class EventHistoryServiceTest {

    @Autowired
    EventHistoryService eventHistoryService;

    @MockBean
    Subscription subscriptionMock;

    @MockBean
    EventHistoryRepository eventHistoryRepositoryMock;

    @Test
    @DisplayName("Should register a new history")
    public void registerHistory_shouldRegisterNewHistory() {
        final String type = "Mock Type";

        EventHistory eventHistory = eventHistoryService.registerHistory(subscriptionMock, type);

        verify(eventHistoryRepositoryMock).save(eventHistory);
    }
}
