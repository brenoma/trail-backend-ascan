package br.com.atlantico.brenoararipe.subsapi.controller;

import br.com.atlantico.brenoararipe.consumermicroservice.model.Subscription;
import br.com.atlantico.brenoararipe.consumermicroservice.repository.SubscriptionRepository;
//import br.com.atlantico.brenoararipe.subsapi.form.CreateSubscriptionForm;
import br.com.atlantico.brenoararipe.subsapi.service.RabbitmqService;
import constants.SubscriptionStatusConstants;
import dto.SubscriptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static constants.RabbitMQConstants.QUEUE_SUBSCRIPTION;
import static constants.SubscriptionStatusConstants.ACTIVE;
import static constants.SubscriptionStatusConstants.INACTIVE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Controller class for subscriptions
 */
@RestController
@RequestMapping("/api/v1/subscription")
public class SubscriptionController {

    @Autowired
    private RabbitmqService rabbitmqService;

    /**
     * Creation of a subscription
     *
     * @param
     */
    @RequestMapping(value = "/register", method = POST)
    public ResponseEntity register(@RequestBody SubscriptionDto subscriptionDto) {
        subscriptionDto.status_id = INACTIVE;
        System.out.println(subscriptionDto.email);
        System.out.println(subscriptionDto.status_id);
//        this.rabbitmqService.sendMessage(QUEUE_SUBSCRIPTION, subscriptionDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /**
     * The purchase was made.
     *
     * @param subscriptionDto DTO with id and status_id payloads serialized.
     */
    @RequestMapping(value = "/purchase", method = PUT)
    public ResponseEntity purchaseSubscription(@RequestBody SubscriptionDto subscriptionDto) {
        subscriptionDto.status_id = ACTIVE;
        this.rabbitmqService.sendMessage(QUEUE_SUBSCRIPTION, subscriptionDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * The purchase was canceled.
     *
     * @param subscriptionDto DTO with id and status_id payloads serialized.
     */
    @RequestMapping(value = "/cancel", method = PUT)
    public ResponseEntity cancelSubscription(@RequestBody SubscriptionDto subscriptionDto) {
        subscriptionDto.status_id = INACTIVE;
        this.rabbitmqService.sendMessage(QUEUE_SUBSCRIPTION, subscriptionDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * The purchase was recovered.
     *
     * @param subscriptionDto DTO with id and status_id payloads serialized.
     */
    @RequestMapping(value = "/recover", method = PUT)
    public ResponseEntity restartSubscription(@RequestBody SubscriptionDto subscriptionDto) {
        subscriptionDto.status_id = ACTIVE;
        this.rabbitmqService.sendMessage(QUEUE_SUBSCRIPTION, subscriptionDto);
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
