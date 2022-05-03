package br.com.atlantico.brenoararipe.consumermicroservice.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "subscription", schema = "public")
@Data
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String status_id;

    @NotNull
    @CreationTimestamp
    private LocalDateTime created_at = LocalDateTime.now();

    @UpdateTimestamp
    private LocalDateTime updated_at;

    public Subscription() {}

    public Subscription(String email, String status_id) {
        this.email = email;
        this.status_id = status_id;
    }
}
