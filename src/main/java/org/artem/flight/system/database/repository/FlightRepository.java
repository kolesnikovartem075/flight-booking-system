package org.artem.flight.system.database.repository;

import org.artem.flight.system.database.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}