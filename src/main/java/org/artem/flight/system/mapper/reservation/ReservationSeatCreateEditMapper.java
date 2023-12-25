package org.artem.flight.system.mapper.reservation;

import lombok.AllArgsConstructor;
import org.artem.flight.system.database.entity.ReservationSeat;
import org.artem.flight.system.database.entity.Seat;
import org.artem.flight.system.database.repository.SeatRepository;
import org.artem.flight.system.dto.ReservationSeatCreateEditDto;
import org.artem.flight.system.mapper.Mapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ReservationSeatCreateEditMapper implements Mapper<ReservationSeatCreateEditDto, ReservationSeat> {

    private final SeatRepository seatRepository;

    public ReservationSeat map(ReservationSeatCreateEditDto object) {
        var reservationSeat = new ReservationSeat();
        copy(object, reservationSeat);

        return reservationSeat;
    }

    public ReservationSeat map(ReservationSeatCreateEditDto fromObject, ReservationSeat toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(ReservationSeatCreateEditDto fromObject, ReservationSeat toObject) {
        var seat = getSeat(fromObject);

        toObject.setPrice(fromObject.getPrice());
        toObject.setStatus(fromObject.getStatus());
        toObject.setSeat(seat);
    }

    private Seat getSeat(ReservationSeatCreateEditDto fromObject) {
        return seatRepository.findById(fromObject.getSeatId())
                .orElseThrow();
    }
}
