package org.artem.flight.system.mapper.airport;

import org.artem.flight.system.database.entity.Airport;
import org.artem.flight.system.dto.AirportReadDto;
import org.artem.flight.system.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AirportReadMapper implements Mapper<Airport, AirportReadDto> {


    @Override
    public AirportReadDto map(Airport object) {
        return null;
    }
}
