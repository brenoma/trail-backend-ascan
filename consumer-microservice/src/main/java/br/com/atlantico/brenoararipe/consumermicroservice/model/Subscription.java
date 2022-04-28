package br.com.atlantico.brenoararipe.consumermicroservice.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "subscription", schema = "public")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String status_id;

    @NotNull
    @CreationTimestamp
    private LocalDateTime created_at = LocalDateTime.now();

    @UpdateTimestamp
    private LocalDateTime updated_at;

    public Subscription(String status_id) {
        this.status_id = status_id;
    }

}
