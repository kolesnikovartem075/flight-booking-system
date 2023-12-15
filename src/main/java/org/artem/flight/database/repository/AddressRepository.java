package org.artem.flight.database.repository;

import org.artem.flight.database.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}