package br.com.atlantico.brenoararipe.subsapi.controller;

import br.com.atlantico.brenoararipe.subsapi.service.RabbitmqService;
import dto.SubscriptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static constants.RabbitMQConstants.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Controller class for subscriptions.
 *
 */
@RestController
@RequestMapping("/api/v1/subscription")
public class SubscriptionController {

    /**
     * RabbitmqService instance.
     *
     */
    @Autowired
    private RabbitmqService rabbitmqService;

    /**
     * Creation of a subscription.
     *
     * @param subscriptionDto {@link SubscriptionDto}
     * @return {@link ResponseEntity}
     */
    @RequestMapping(value = "/register", method = POST)
    public ResponseEntity register(@RequestBody SubscriptionDto subscriptionDto) {
        this.rabbitmqService.sendMessage(QUEUE_REGISTER, subscriptionDto);
        return new ResponseEntity(subscriptionDto, HttpStatus.CREATED);
    }

    /**
     * The subscription was purchased.
     *
     * @param subscriptionDto {@link SubscriptionDto}
     * @return {@link ResponseEntity}
     */
    @RequestMapping(value = "/purchase", method = PUT)
    public ResponseEntity purchaseSubscription(@RequestBody SubscriptionDto subscriptionDto) {
        this.rabbitmqService.sendMessage(QUEUE_PURCHASE, subscriptionDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * The subscription was canceled.
     *
     * @param subscriptionDto {@link SubscriptionDto}
     * @return {@link ResponseEntity}
     */
    @RequestMapping(value = "/cancel", method = PUT)
    public ResponseEntity cancelSubscription(@RequestBody SubscriptionDto subscriptionDto) {
        this.rabbitmqService.sendMessage(QUEUE_CANCEL, subscriptionDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * The subscription was recovered.
     *
     * @param subscriptionDto {@link SubscriptionDto}
     * @return {@link ResponseEntity}
     */
    @RequestMapping(value = "/recover", method = PUT)
    public ResponseEntity restartSubscription(@RequestBody SubscriptionDto subscriptionDto) {
        this.rabbitmqService.sendMessage(QUEUE_RECOVER, subscriptionDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Health Check Subscription API.
     *
     * @return a {@link String} response with the status of the controller.
     */
    @RequestMapping(value = "/health", method = GET)
    public String check() {
        return "Subscription Endpoint OK!!!";
    }
}
