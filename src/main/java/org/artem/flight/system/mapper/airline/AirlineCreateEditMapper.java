package org.artem.flight.system.mapper.airline;

import org.artem.flight.system.database.entity.Airline;
import org.artem.flight.system.dto.AirlineCreateEditDto;
import org.artem.flight.system.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AirlineCreateEditMapper implements Mapper<AirlineCreateEditDto, Airline> {


    @Override
    public Airline map(AirlineCreateEditDto object) {
        return null;
    }

    @Override
    public Airline map(AirlineCreateEditDto fromObject, Airline toObject) {
        return Mapper.super.map(fromObject, toObject);
    }
}
