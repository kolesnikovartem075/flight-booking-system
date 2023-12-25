package org.artem.flight.system.mapper.flight;

import lombok.AllArgsConstructor;
import org.artem.flight.system.database.entity.Flight;
import org.artem.flight.system.dto.AirlineReadDto;
import org.artem.flight.system.dto.AirportReadDto;
import org.artem.flight.system.dto.FlightReadDto;
import org.artem.flight.system.dto.SeatReadDto;
import org.artem.flight.system.mapper.Mapper;
import org.artem.flight.system.mapper.airline.AirlineReadMapper;
import org.artem.flight.system.mapper.airport.AirportReadMapper;
import org.artem.flight.system.mapper.seat.SeatReadMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class FlightReadMapper implements Mapper<Flight, FlightReadDto> {

    private final AirportReadMapper airportReadMapper;
    private final AirlineReadMapper airlineReadMapper;
    private final SeatReadMapper seatReadMapper;


    public FlightReadDto map(Flight object) {
        var airport = getAirport(object);
        var airline = getAirline(object);
        var seats = getSeats(object);

        return FlightReadDto.builder()
                .id(object.getId())
                .flightNo(object.getFlightNo())
                .airport(airport)
                .airline(airline)
                .seats(seats)
                .seatCapacity(seats.size())
                .build();
    }

    private AirportReadDto getAirport(Flight object) {
        return airportReadMapper.map(object.getAirport());
    }

    private AirlineReadDto getAirline(Flight object) {
        return airlineReadMapper.map(object.getAirline());
    }

    private List<SeatReadDto> getSeats(Flight object) {
        return object.getSeats().stream()
                .map(seatReadMapper::map)
                .toList();
    }
}