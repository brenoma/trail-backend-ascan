package br.com.atlantico.brenoararipe.consumermicroservice.model.EventHistory;

import br.com.atlantico.brenoararipe.consumermicroservice.model.Subscription.Subscription;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class EventHistoryBuilderTest {

    @MockBean
    EventHistoryBuilder eventHistoryBuilder;

    @Test
    @DisplayName("Should build a new EventHistory object")
    public void builder_ShouldReturnNewEventHistory() {

        EventHistory eventHistory = EventHistoryBuilder
                .builder()
                .build();

        assertEquals(EventHistory.class, eventHistory.getClass());
    }

    @Test
    @DisplayName("Should build a new EventHistory object with Subscription")
    public void builder_ShouldReturnNewEventHistoryWithSubscription() {
        final Subscription subscription = new Subscription();

        EventHistory eventHistory = EventHistoryBuilder
                .builder()
                .subscription(subscription)
                .build();

        assertEquals(eventHistory.getSubscription(), subscription);
    }

    @Test
    @DisplayName("Should build a new EventHistory object with Type")
    public void builder_ShouldReturnNewEventHistoryWithType() {
        final String type = "Mock Type";

        EventHistory eventHistory = EventHistoryBuilder
                .builder()
                .type(type)
                .build();

        assertEquals(eventHistory.getType(), type);
    }
}
