package org.artem.flight.system.mapper.schedule;

import lombok.AllArgsConstructor;
import org.artem.flight.system.database.entity.Airport;
import org.artem.flight.system.database.entity.Schedule;
import org.artem.flight.system.dto.AirportReadDto;
import org.artem.flight.system.dto.FlightReadDto;
import org.artem.flight.system.dto.ReservationSeatReadDto;
import org.artem.flight.system.dto.ScheduleReadDto;
import org.artem.flight.system.mapper.Mapper;
import org.artem.flight.system.mapper.airport.AirportReadMapper;
import org.artem.flight.system.mapper.flight.FlightReadMapper;
import org.artem.flight.system.mapper.reservation.ReservationSeatReadMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ScheduleReadMapper implements Mapper<Schedule, ScheduleReadDto> {


    private final FlightReadMapper flightReadMapper;
    private final AirportReadMapper airportReadMapper;
    private final ReservationSeatReadMapper reservationSeatReadMapper;


    public ScheduleReadDto map(Schedule object) {
        var flight = getFlight(object);
        var startAirport = getAirport(object.getStart());
        var endAirport = getAirport(object.getDestination());
        var reservationSeats = getReservationSeats(object);

        return ScheduleReadDto.builder()
                .flight(flight)
                .start(startAirport)
                .destination(endAirport)
                .startTime(object.getStartTime())
                .endTime(object.getEndTime())
                .status(object.getStatus())
                .reservationSeats(reservationSeats)
                .build();
    }

    private List<ReservationSeatReadDto> getReservationSeats(Schedule object) {
        return object.getReservationSeats().stream().map(reservationSeatReadMapper::map).toList();
    }

    private AirportReadDto getAirport(Airport object) {
        return airportReadMapper.map(object);
    }

    private FlightReadDto getFlight(Schedule object) {
        return flightReadMapper.map(object.getFlight());
    }
}
