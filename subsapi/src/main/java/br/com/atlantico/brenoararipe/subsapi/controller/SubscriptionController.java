package br.com.atlantico.brenoararipe.subsapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * Controller class for subscriptions
 */
@RestController
@RequestMapping("/api/v1/subscription")
public class SubscriptionController {

    /**
     * The purchase was made.
     *
     * @param subscriptionDTO
     */
    @RequestMapping(value = "/purchase" ,method = PUT)
    public ResponseEntity purchaseSubscription(@RequestBody Object subscriptionDTO) {
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * The purchase was canceled.
     */
    @RequestMapping(value = "/cancel" ,method = PUT)
    public String cancelSubscription() {
        return "Canceling your subscription...";
    }

    /**
     * The purchase was recovered.
     */
    @RequestMapping(value = "/restart" ,method = PUT)
    public String restartSubscription() {
        return "Restarting your subscription...";
    }

    /**
     * Health Check Subscription API.
     *
     * @return a {@link String} response with the status of the controller.
     */
    @RequestMapping(value = "/health" ,method = GET)
    public String check() {
        return "Subscription Endpoint OK!!!";
    }
}
