package org.artem.flight.system.database.repository;

import org.artem.flight.system.database.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Optional<Schedule> findByReservationSeatsId(Long id);
}