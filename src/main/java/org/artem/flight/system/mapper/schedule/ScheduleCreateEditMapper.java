package org.artem.flight.system.mapper.schedule;

import org.artem.flight.system.database.entity.Schedule;
import org.artem.flight.system.dto.ScheduleCreateEditDto;
import org.artem.flight.system.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ScheduleCreateEditMapper implements Mapper<ScheduleCreateEditDto, Schedule> {


    public Schedule map(ScheduleCreateEditDto object) {
        return null;
    }

    public Schedule map(ScheduleCreateEditDto fromObject, Schedule toObject) {
        return null;
    }
}
