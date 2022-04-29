package dto;

import java.io.Serializable;

/**
 * Class to serialize Subscription payload into bytes.
 */
public class SubscriptionDto implements Serializable {
    public Long id;
    public String email;
    public String status_id;
}
