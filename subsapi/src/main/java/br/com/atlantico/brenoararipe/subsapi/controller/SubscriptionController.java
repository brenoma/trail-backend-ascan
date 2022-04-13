package br.com.atlantico.brenoararipe.subsapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subscription")
public class SubscriptionController {

    // PURCHASE
    @PutMapping("/purchase")
    public ResponseEntity purchaseSubscription(@RequestBody Object subscriptionDTO) {
        return new ResponseEntity(HttpStatus.OK);
    }

    // CANCELED
    @PutMapping("/cancel")
    public String cancelSubscription() {
        return "Canceling your subscription...";
    }

    // RESTARTED
    @PutMapping("/restart")
    public String restartSubscription() {
        return "Restarting your subscription...";
    }

    @GetMapping("/health")
    public String check() {
        return "Subscription Endpoint OK!!!";
    }
}
