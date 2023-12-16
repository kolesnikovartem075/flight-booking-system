package org.artem.flight.system.mapper.flight;

import org.artem.flight.system.database.entity.Flight;
import org.artem.flight.system.dto.FlightCreateEditDto;
import org.artem.flight.system.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class FlightCreateEditMapper implements Mapper<FlightCreateEditDto, Flight> {


    public Flight map(FlightCreateEditDto object) {
        return null;
    }

    public Flight map(FlightCreateEditDto fromObject, Flight toObject) {
        return null;
    }
}
