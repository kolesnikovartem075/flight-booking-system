package org.artem.flight.system.database.repository;

import org.artem.flight.system.database.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}