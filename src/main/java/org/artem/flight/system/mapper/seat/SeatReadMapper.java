package org.artem.flight.system.mapper.seat;

import org.artem.flight.system.database.entity.Seat;
import org.artem.flight.system.dto.SeatReadDto;
import org.artem.flight.system.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class SeatReadMapper implements Mapper<Seat, SeatReadDto> {
    public SeatReadDto map(Seat object) {
        return null;
    }
}
