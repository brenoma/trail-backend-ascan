package br.com.atlantico.brenoararipe.consumermicroservice.repository;

import br.com.atlantico.brenoararipe.consumermicroservice.model.Subscription.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to provides the mechanism for storage, retrieval, update, delete and search operation on objects.
 *
 */
@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    public Subscription findByEmail(String email);
}
