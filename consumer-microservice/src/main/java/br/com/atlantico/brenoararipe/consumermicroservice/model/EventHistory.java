package br.com.atlantico.brenoararipe.consumermicroservice.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "event_history", schema = "public")
@Data
public class EventHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String type;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    public EventHistory() {}

    public EventHistory(Subscription subscription, String type) {
        this.type = type;
        this.subscription = subscription;
    }
}
