package br.com.atlantico.brenoararipe.consumermicroservice.service;

import br.com.atlantico.brenoararipe.consumermicroservice.model.EventHistory.EventHistory;
import br.com.atlantico.brenoararipe.consumermicroservice.model.Subscription.Subscription;
import br.com.atlantico.brenoararipe.consumermicroservice.repository.SubscriptionRepository;
import dto.SubscriptionDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=SubscriptionService.class, loader= AnnotationConfigContextLoader.class)
public class SubscriptionServiceTest {

    @Autowired
    SubscriptionService subscriptionService;

    @MockBean
    SubscriptionDto subscriptionDtoMock;

    @MockBean
    Subscription subscriptionMock;

    @MockBean
    EventHistoryService eventHistoryServiceMock;

    @MockBean
    RabbitmqService rabbitmqServiceMock;

    @MockBean
    SubscriptionRepository subscriptionRepositoryMock;

    final String type = "Mock Type";
    final String status = "Mock Status";

    @Test
    @DisplayName("Should register a new subscription with email already registered")
    public void registerSubscription_shouldRegisterNewSubscriptionWithEmailAlreadyRegistered() {
        when(subscriptionRepositoryMock.findByEmail(subscriptionDtoMock.email)).thenReturn(subscriptionMock);

        Subscription subscription = subscriptionService.registerSubscription(subscriptionDtoMock, type, status);

        assertEquals(Subscription.class, subscription.getClass());
    }

    @Test
    @DisplayName("Should register a new subscription with new email")
    public void registerSubscription_shouldRegisterNewSubscriptionWithNewEmail() {
        when(subscriptionRepositoryMock.findByEmail(subscriptionDtoMock.email)).thenReturn(null);
        when(eventHistoryServiceMock.registerHistory(subscriptionMock, type)).thenReturn(new EventHistory());

        Subscription subscription = subscriptionService.registerSubscription(subscriptionDtoMock, type, status);

        verify(subscriptionRepositoryMock).save(subscription);
    }

    @Test
    @DisplayName("Should update a subscription")
    public void updateSubscription_shouldUpdateValidSubscription() {
        when(subscriptionRepositoryMock.findByEmail(subscriptionDtoMock.email)).thenReturn(subscriptionMock);
        when(eventHistoryServiceMock.registerHistory(subscriptionMock, type)).thenReturn(new EventHistory());

        Subscription subscription = subscriptionService.updateSubscription(subscriptionDtoMock, type, status);

        verify(subscriptionRepositoryMock).save(subscription);
    }

    @Test
    @DisplayName("Should not update an invalid subscription")
    public void updateSubscription_shouldNotUpdateInvalidSubscription() {
        when(subscriptionRepositoryMock.findByEmail(subscriptionDtoMock.email)).thenReturn(null);

        Subscription subscription = subscriptionService.updateSubscription(subscriptionDtoMock, type, status);

        assertEquals(Subscription.class, subscription.getClass());
    }
}
