package org.artem.flight.system.database.repository;

import org.artem.flight.system.database.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}