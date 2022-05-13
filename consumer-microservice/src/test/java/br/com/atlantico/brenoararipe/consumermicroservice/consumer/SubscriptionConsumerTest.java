package br.com.atlantico.brenoararipe.consumermicroservice.consumer;

import br.com.atlantico.brenoararipe.consumermicroservice.service.SubscriptionService;
import dto.SubscriptionDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static constants.SubscriptionStatusConstants.ACTIVE;
import static constants.SubscriptionStatusConstants.INACTIVE;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= SubscriptionConsumer.class, loader= AnnotationConfigContextLoader.class)
public class SubscriptionConsumerTest {

    @Autowired
    SubscriptionConsumer subscriptionConsumer;

    @MockBean
    SubscriptionDto subscriptionDtoMock;

    @MockBean
    SubscriptionService subscriptionServiceMock;

    @Test
    @DisplayName("Should register a new Subscription purchase")
    public void updateSubscription_ShouldRegisterSubscriptionPurchase() {
        final String SUBSCRIPTION_PURCHASE = "PURCHASE";

        subscriptionConsumer.subscriptionPurchaseConsumer(subscriptionDtoMock);

        verify(subscriptionServiceMock).updateSubscription(subscriptionDtoMock, SUBSCRIPTION_PURCHASE, ACTIVE);
    }

    @Test
    @DisplayName("Should register a new Subscription cancel")
    public void updateSubscription_ShouldRegisterSubscriptionCancel() {
        final String SUBSCRIPTION_CANCEL = "CANCEL";

        subscriptionConsumer.subscriptionCancelConsumer(subscriptionDtoMock);

        verify(subscriptionServiceMock).updateSubscription(subscriptionDtoMock, SUBSCRIPTION_CANCEL, INACTIVE);
    }

    @Test
    @DisplayName("Should register a new Subscription recover")
    public void updateSubscription_ShouldRegisterSubscriptionRecover() {
        final String SUBSCRIPTION_RECOVER = "RECOVER";

        subscriptionConsumer.subscriptionRecoverConsumer(subscriptionDtoMock);

        verify(subscriptionServiceMock).updateSubscription(subscriptionDtoMock, SUBSCRIPTION_RECOVER, ACTIVE);
    }

    @Test
    @DisplayName("Should register a new Subscription")
    public void registerSubscription_ShouldRegisterNewSubscription() {
        final String SUBSCRIPTION_CREATE = "CREATE";

        subscriptionConsumer.subscriptionRegisterConsumer(subscriptionDtoMock);

        verify(subscriptionServiceMock).registerSubscription(subscriptionDtoMock, SUBSCRIPTION_CREATE, INACTIVE);
    }
}
