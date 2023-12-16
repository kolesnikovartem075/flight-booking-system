package org.artem.flight.system.mapper.flight;

import org.artem.flight.system.database.entity.Flight;
import org.artem.flight.system.dto.FlightReadDto;
import org.artem.flight.system.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class FlightReadMapper implements Mapper<Flight, FlightReadDto> {


    public FlightReadDto map(Flight object) {
        return null;
    }
}
