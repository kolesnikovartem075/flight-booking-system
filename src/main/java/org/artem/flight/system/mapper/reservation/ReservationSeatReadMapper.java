package org.artem.flight.system.mapper.reservation;

import lombok.AllArgsConstructor;
import org.artem.flight.system.database.entity.ReservationSeat;
import org.artem.flight.system.dto.ReservationSeatReadDto;
import org.artem.flight.system.dto.SeatReadDto;
import org.artem.flight.system.mapper.Mapper;
import org.artem.flight.system.mapper.seat.SeatReadMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ReservationSeatReadMapper implements Mapper<ReservationSeat, ReservationSeatReadDto> {

    private final SeatReadMapper seatReadMapper;

    public ReservationSeatReadDto map(ReservationSeat object) {
        var seat = getSeat(object);

        return new ReservationSeatReadDto(object.getId(), seat, object.getStatus().name(), object.getPrice());
    }

    private SeatReadDto getSeat(ReservationSeat object) {
        return seatReadMapper.map(object.getSeat());
    }
}