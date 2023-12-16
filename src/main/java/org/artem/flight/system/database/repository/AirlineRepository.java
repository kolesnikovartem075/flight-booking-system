package org.artem.flight.system.database.repository;

import org.artem.flight.system.database.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
}