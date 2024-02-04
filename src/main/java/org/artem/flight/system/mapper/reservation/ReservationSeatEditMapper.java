package org.artem.flight.system.mapper.reservation;

import lombok.AllArgsConstructor;
import org.artem.flight.system.database.entity.ReservationSeat;
import org.artem.flight.system.database.entity.Seat;
import org.artem.flight.system.database.repository.ScheduleRepository;
import org.artem.flight.system.database.repository.SeatRepository;
import org.artem.flight.system.dto.ReservationSeatEditDto;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ReservationSeatEditMapper {

SeatRepository seatRepository;
    public ReservationSeat map(ReservationSeatEditDto fromObject, ReservationSeat toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(ReservationSeatEditDto fromObject, ReservationSeat toObject) {
        var seat = getSeat(fromObject);

        toObject.setPrice(fromObject.getPrice());
        toObject.setSeat(seat);
    }

    private Seat getSeat(ReservationSeatEditDto fromObject) {
        return seatRepository.findById(fromObject.getSeatId()).orElseThrow();
    }
}
