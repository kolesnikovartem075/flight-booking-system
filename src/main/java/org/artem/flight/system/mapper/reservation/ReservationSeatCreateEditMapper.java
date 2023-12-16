package org.artem.flight.system.mapper.reservation;

import org.artem.flight.system.database.entity.ReservationSeat;
import org.artem.flight.system.dto.ReservationSeatCreateEditDto;
import org.artem.flight.system.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ReservationSeatCreateEditMapper implements Mapper<ReservationSeatCreateEditDto, ReservationSeat> {


    public ReservationSeat map(ReservationSeatCreateEditDto object) {
        return null;
    }

    public ReservationSeat map(ReservationSeatCreateEditDto fromObject, ReservationSeat toObject) {
        return null;
    }
}
