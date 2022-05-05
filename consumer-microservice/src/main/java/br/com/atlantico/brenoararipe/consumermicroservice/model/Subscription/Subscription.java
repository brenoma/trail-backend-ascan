package br.com.atlantico.brenoararipe.consumermicroservice.model.Subscription;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * This class represents a Subscription object and its data.
 *
 */
@Entity
@Table(name = "subscription", schema = "public")
@Data
public class Subscription {

    /**
     * Id of the subscription.
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Email of the subscription owner.
     *
     */
    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    /**
     * StatusId of the subscription.
     *
     */
    @NotNull
    @Column(name = "status_id")
    private String statusId;

    /**
     * Timestamp of the subscription creation.
     *
     */
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * Timestamp of the subscription modification.
     *
     */
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
