package org.artem.flight.system.database.repository;

import org.artem.flight.system.database.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}