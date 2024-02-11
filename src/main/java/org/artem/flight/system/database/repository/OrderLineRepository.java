package org.artem.flight.system.database.repository;

import org.artem.flight.system.database.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

}
