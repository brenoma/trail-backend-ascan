package br.com.atlantico.brenoararipe.consumermicroservice.model.Subscription;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class SubscriptionTest {

    @SpyBean
    Subscription subscriptionMock;

    @Test
    @DisplayName("Should test id getter and setter for ID")
    public void getAndSetId_shouldSetIdAndGetTheSameValue() {
        final Long id = 1L;

        subscriptionMock.setId(id);

        assertEquals(id, subscriptionMock.getId());
    }

    @Test
    @DisplayName("Should test id getter and setter for email")
    public void getAndSetEmail_shouldSetIdAndGetTheSameValue() {
        final String email = "mock@test.com";

        subscriptionMock.setEmail(email);

        assertEquals(email, subscriptionMock.getEmail());
    }

    @Test
    @DisplayName("Should test id getter and setter for statusId")
    public void getAndSetStatusId_shouldSetIdAndGetTheSameValue() {
        final String statusId = "MOCK STATUS ID";

        subscriptionMock.setStatusId(statusId);

        assertEquals(statusId, subscriptionMock.getStatusId());
    }

    @Test
    @DisplayName("Should test id getter and setter for createdAt")
    public void getAndSetCreatedAt_shouldSetIdAndGetTheSameValue() {
        final LocalDateTime createdAt = LocalDateTime.now();

        subscriptionMock.setCreatedAt(createdAt);

        assertEquals(createdAt, subscriptionMock.getCreatedAt());
    }

    @Test
    @DisplayName("Should test id getter and setter for updatedAt")
    public void getAndSetUpdatedAt_shouldSetIdAndGetTheSameValue() {
        final LocalDateTime updatedAt = LocalDateTime.now();

        subscriptionMock.setUpdatedAt(updatedAt);

        assertEquals(updatedAt, subscriptionMock.getUpdatedAt());
    }
}
