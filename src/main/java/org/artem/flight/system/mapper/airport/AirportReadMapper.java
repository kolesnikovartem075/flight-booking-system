package org.artem.flight.system.mapper.airport;

import lombok.AllArgsConstructor;
import org.artem.flight.system.database.entity.Airport;
import org.artem.flight.system.dto.CityReadDto;
import org.artem.flight.system.dto.AirportReadDto;
import org.artem.flight.system.mapper.Mapper;
import org.artem.flight.system.mapper.city.CityReadMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AirportReadMapper implements Mapper<Airport, AirportReadDto> {

    private final CityReadMapper cityReadMapper;

    @Override
    public AirportReadDto map(Airport object) {
        var city = getCityReadDto(object);

        return new AirportReadDto(object.getId(), object.getName(), city);
    }

    private CityReadDto getCityReadDto(Airport object) {
        return cityReadMapper.map(object.getCity());
    }
}