package org.artem.flight.system.mapper.reservation;

import org.artem.flight.system.database.entity.ReservationSeat;
import org.artem.flight.system.dto.ReservationSeatReadDto;
import org.artem.flight.system.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ReservationSeatReadMapper implements Mapper<ReservationSeat, ReservationSeatReadDto> {


    public ReservationSeatReadDto map(ReservationSeat object) {
        return null;
    }
}