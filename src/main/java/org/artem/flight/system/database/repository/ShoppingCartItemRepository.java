package org.artem.flight.system.database.repository;

import org.artem.flight.system.database.entity.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {

    List<ShoppingCartItem> findAllByShoppingCartId(Long shoppingCartId);
}
