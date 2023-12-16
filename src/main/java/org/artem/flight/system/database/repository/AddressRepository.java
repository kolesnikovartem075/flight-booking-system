package org.artem.flight.system.database.repository;

import org.artem.flight.system.database.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}