package constants;

/**
 * Utility class with constants for RabbitMQ.
 *
 */
public class RabbitMQConstants {
    public static final String QUEUE_PURCHASE = "SUBSCRIPTION - PURCHASE";
    public static final String QUEUE_CANCEL = "SUBSCRIPTION - CANCEL";
    public static final String QUEUE_RECOVER = "SUBSCRIPTION - RECOVER";
    public static final String QUEUE_REGISTER = "SUBSCRIPTION - REGISTER";
    public static final String QUEUE_EMAIL_REGISTER = "EMAIL - REGISTER SUBSCRIPTION";
    public static final String QUEUE_EMAIL_SUBSCRIPTION_UPDATE = "EMAIL - SUBSCRIPTION UPDATE";
}
