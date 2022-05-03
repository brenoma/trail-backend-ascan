package br.com.atlantico.brenoararipe.consumermicroservice.repository;

import br.com.atlantico.brenoararipe.consumermicroservice.model.EventHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventHistoryRepository extends JpaRepository<EventHistory, Long> {
}