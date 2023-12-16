package org.artem.flight.system.mapper.airport;

import org.artem.flight.system.database.entity.Airport;
import org.artem.flight.system.dto.AirportCreateEditDto;
import org.artem.flight.system.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AirportCreateEditMapper implements Mapper<AirportCreateEditDto, Airport> {


    @Override
    public Airport map(AirportCreateEditDto object) {
        return null;
    }

    @Override
    public Airport map(AirportCreateEditDto fromObject, Airport toObject) {
        return Mapper.super.map(fromObject, toObject);
    }
}
