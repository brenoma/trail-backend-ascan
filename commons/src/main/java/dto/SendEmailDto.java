package dto;

import java.io.Serializable;

/**
 * Class to serialize Subscription payload into bytes.
 *
 */
public class SendEmailDto implements Serializable {
    public String email;
    public String type;

    public SendEmailDto(String email, String type) {
        this.email = email;
        this.type = type;
    }
}
