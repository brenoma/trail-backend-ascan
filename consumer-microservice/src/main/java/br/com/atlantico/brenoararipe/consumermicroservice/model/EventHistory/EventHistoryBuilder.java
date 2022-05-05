package br.com.atlantico.brenoararipe.consumermicroservice.model.EventHistory;

import br.com.atlantico.brenoararipe.consumermicroservice.model.Subscription.Subscription;

/**
 * Class designed to build a {@link EventHistory}
 *
 */
public class EventHistoryBuilder {

    /**
     * Object declaration.
     *
     */
    private EventHistory eventHistory;

    /**
     * EventHistoryBuilder constructor.
     * Creates an instance of {@link EventHistory}
     *
     */
    private EventHistoryBuilder() {
        eventHistory = new EventHistory();
    }

    /**
     * Method to initialize object building.
     *
     * @return {@link EventHistoryBuilder}
     */
    public static EventHistoryBuilder builder() {
        return new EventHistoryBuilder();
    }

    /**
     * Method to set subscription.
     *
     * @param subscription {@link Subscription}
     * @return {@link EventHistoryBuilder}
     */
    public EventHistoryBuilder subscription(Subscription subscription) {
        this.eventHistory.setSubscription(subscription);
        return this;
    }

    /**
     * Method to set type.
     *
     * @param type {@link String}
     * @return {@link EventHistoryBuilder}
     */
    public EventHistoryBuilder type(String type) {
        this.eventHistory.setType(type);
        return this;
    }

    /**
     * Method to build the object.
     *
     * @return {@link EventHistory}
     */
    public EventHistory build() {
        return this.eventHistory;
    }
}
