package org.artem.flight.system.database.repository;

import org.artem.flight.system.database.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}