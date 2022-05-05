package br.com.atlantico.brenoararipe.consumermicroservice.model.Subscription;

/**
 * Class designed to build a {@link Subscription}
 *
 */
public class SubscriptionBuilder {

    /**
     * Object declaration.
     *
     */
    private Subscription subscription;

    /**
     * SubscriptionBuilder constructor.
     * Creates an instance of {@link Subscription}
     *
     */
    private SubscriptionBuilder() {
        subscription = new Subscription();
    }

    /**
     * Method to initialize object building.
     *
     * @return {@link SubscriptionBuilder}
     */
    public static SubscriptionBuilder builder() {
        return new SubscriptionBuilder();
    }

    /**
     * Method to set email.
     *
     * @param email {@link String}
     * @return {@link SubscriptionBuilder}
     */
    public SubscriptionBuilder email(String email) {
        this.subscription.setEmail(email);
        return this;
    }

    /**
     * Method to set statusId
     *
     * @param statusId {@link String}
     * @return {@link SubscriptionBuilder}
     */
    public SubscriptionBuilder statusId(String statusId) {
        this.subscription.setStatusId(statusId);
        return this;
    }


    public Subscription build() {
        return this.subscription;
    }
}
