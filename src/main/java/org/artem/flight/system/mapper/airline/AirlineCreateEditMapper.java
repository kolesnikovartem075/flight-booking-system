package org.artem.flight.system.mapper.airline;

import org.artem.flight.system.database.entity.Airline;
import org.artem.flight.system.dto.AirlineCreateEditDto;
import org.artem.flight.system.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AirlineCreateEditMapper implements Mapper<AirlineCreateEditDto, Airline> {


    @Override
    public Airline map(AirlineCreateEditDto object) {
        var airline = new Airline();
        copy(object, airline);

        return airline;
    }

    @Override
    public Airline map(AirlineCreateEditDto fromObject, Airline toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(AirlineCreateEditDto fromObject, Airline target) {
        target.setTitle(fromObject.getTitle());
    }
}
