package org.artem.flight.database.repository;

import org.artem.flight.database.entity.ReservationSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationSeatRepository extends JpaRepository<ReservationSeat, Long> {
}