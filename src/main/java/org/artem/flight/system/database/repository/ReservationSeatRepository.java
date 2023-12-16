package org.artem.flight.system.database.repository;

import org.artem.flight.system.database.entity.ReservationSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationSeatRepository extends JpaRepository<ReservationSeat, Long> {
}