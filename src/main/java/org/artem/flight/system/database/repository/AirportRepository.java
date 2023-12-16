package org.artem.flight.system.database.repository;

import org.artem.flight.system.database.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}