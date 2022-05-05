package br.com.atlantico.brenoararipe.consumermicroservice.model.EventHistory;

import br.com.atlantico.brenoararipe.consumermicroservice.model.Subscription.Subscription;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * This class represents a EventHistory object and its data.
 *
 */
@Entity
@Table(name = "event_history", schema = "public")
@Data
public class EventHistory {

    /**
     * Id of the EventHistory (Primary Key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Type of event for record.
     */
    @NotNull
    private String type;

    /**
     * Timestamp of the EventHistory creation.
     */
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * Id of the Subscription (Foreign Key).
     */
    @OneToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;
}