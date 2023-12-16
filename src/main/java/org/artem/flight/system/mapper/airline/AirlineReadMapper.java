package org.artem.flight.system.mapper.airline;

import org.artem.flight.system.database.entity.Airline;
import org.artem.flight.system.dto.AirlineReadDto;
import org.artem.flight.system.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AirlineReadMapper implements Mapper<Airline, AirlineReadDto> {


    @Override
    public AirlineReadDto map(Airline object) {
        return null;
    }
}
