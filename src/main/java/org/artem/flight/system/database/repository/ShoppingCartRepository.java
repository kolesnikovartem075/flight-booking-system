package org.artem.flight.system.database.repository;

import org.artem.flight.system.database.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    @Query("""
            SELECT sc FROM ShoppingCart sc
            WHERE sc.sessionId = :sessionId
            """)
    Optional<ShoppingCart> findBySessionId(String sessionId);
}