package br.com.atlantico.brenoararipe.subsapi.dto;

import java.io.Serializable;

/**
 * Class to serialize Subscription payload into bytes.
 */
public class SubscriptionDto implements Serializable {
    public String id;
    public String status_id;
}
