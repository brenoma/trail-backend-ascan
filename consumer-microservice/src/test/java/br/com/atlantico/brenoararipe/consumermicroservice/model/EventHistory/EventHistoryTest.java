package br.com.atlantico.brenoararipe.consumermicroservice.model.EventHistory;

import br.com.atlantico.brenoararipe.consumermicroservice.model.Subscription.Subscription;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class EventHistoryTest {

    @SpyBean
    EventHistory eventHistoryMock;

    @Test
    @DisplayName("Should test id getter and setter for ID")
    public void getAndSetId_shouldSetIdAndGetTheSameValue() {
        final Long id = 1L;

        eventHistoryMock.setId(id);

        assertEquals(id, eventHistoryMock.getId());
    }

    @Test
    @DisplayName("Should test id getter and setter for Type")
    public void getAndSetType_shouldSetIdAndGetTheSameValue() {
        final String type = "Mock Type";

        eventHistoryMock.setType(type);

        assertEquals(type, eventHistoryMock.getType());
    }

    @Test
    @DisplayName("Should test id getter and setter for creation timestamp")
    public void getAndSetCreatedAt_shouldSetIdAndGetTheSameValue() {
        final LocalDateTime createdAt = LocalDateTime.now();

        eventHistoryMock.setCreatedAt(createdAt);

        assertEquals(createdAt, eventHistoryMock.getCreatedAt());
    }

    @Test
    @DisplayName("Should test id getter and setter for Subscription")
    public void getAndSetSubscription_shouldSetIdAndGetTheSameValue() {
        final Subscription subscription = new Subscription();

        eventHistoryMock.setSubscription(subscription);

        assertEquals(subscription, eventHistoryMock.getSubscription());
    }
}
