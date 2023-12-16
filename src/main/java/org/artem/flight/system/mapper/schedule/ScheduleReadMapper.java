package org.artem.flight.system.mapper.schedule;

import org.artem.flight.system.database.entity.Address;
import org.artem.flight.system.database.entity.Schedule;
import org.artem.flight.system.dto.AddressReadDto;
import org.artem.flight.system.dto.ScheduleReadDto;
import org.artem.flight.system.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ScheduleReadMapper implements Mapper<Schedule, ScheduleReadDto> {
    public ScheduleReadDto map(Schedule object) {
        return null;
    }
}
