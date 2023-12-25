package org.artem.flight.system.mapper.seat;

import lombok.AllArgsConstructor;
import org.artem.flight.system.database.entity.Airline;
import org.artem.flight.system.database.entity.Flight;
import org.artem.flight.system.database.entity.Seat;
import org.artem.flight.system.database.repository.FlightRepository;
import org.artem.flight.system.dto.SeatCreateEditDto;
import org.artem.flight.system.mapper.Mapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SeatCreateEditMapper implements Mapper<SeatCreateEditDto, Seat> {


    private final FlightRepository flightRepository;

    public Seat map(SeatCreateEditDto object) {
        var seat = new Seat();
        copy(object, seat);

        return seat;
    }

    public Seat map(SeatCreateEditDto fromObject, Seat toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(SeatCreateEditDto fromObject, Seat toObject) {
        var flight = getFlight(fromObject);

        toObject.setFlight(flight);
        toObject.setNumberNo(fromObject.getNumberNo());
        toObject.setRank(fromObject.getRank());
    }

    private Flight getFlight(SeatCreateEditDto fromObject) {
        return flightRepository.findById(fromObject.getFlightId()).orElseThrow();
    }
}