package org.artem.flight.system.mapper.seat;

import org.artem.flight.system.database.entity.Seat;
import org.artem.flight.system.dto.SeatCreateEditDto;
import org.artem.flight.system.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class SeatCreateEditMapper implements Mapper<SeatCreateEditDto, Seat> {


    public Seat map(SeatCreateEditDto object) {
        return null;
    }

    public Seat map(SeatCreateEditDto fromObject, Seat toObject) {
        return null;
    }
}