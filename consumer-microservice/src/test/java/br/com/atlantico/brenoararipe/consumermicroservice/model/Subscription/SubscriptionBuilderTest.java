package br.com.atlantico.brenoararipe.consumermicroservice.model.Subscription;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class SubscriptionBuilderTest {

    @MockBean
    SubscriptionBuilder subscriptionBuilderMock;

    @Test
    @DisplayName("Should build a new Subscription object")
    public void builder_ShouldReturnNewSubscription() {

        Subscription subscription = SubscriptionBuilder
                .builder()
                .build();

        assertEquals(Subscription.class, subscription.getClass());
    }

    @Test
    @DisplayName("Should build a new Subscription object with email")
    public void builder_ShouldReturnNewSubscriptionWithEmail() {
        final String email = "mock@test.com";

        Subscription subscription = SubscriptionBuilder
                .builder()
                .email(email)
                .build();

        assertEquals(email, subscription.getEmail());
    }

    @Test
    @DisplayName("Should build a new Subscription object with statusId")
    public void builder_ShouldReturnNewSubscriptionStatusId() {
        final String statusId = "Mock statusId";

        Subscription subscription = SubscriptionBuilder
                .builder()
                .statusId(statusId)
                .build();

        assertEquals(statusId, subscription.getStatusId());
    }
}
