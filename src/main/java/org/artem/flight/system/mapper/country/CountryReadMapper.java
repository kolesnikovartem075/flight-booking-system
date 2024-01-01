package org.artem.flight.system.mapper.country;

import org.artem.flight.system.database.entity.Country;
import org.artem.flight.system.dto.CountryReadDto;
import org.artem.flight.system.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class CountryReadMapper implements Mapper<Country, CountryReadDto> {
    public CountryReadDto map(Country object) {
        return new CountryReadDto(object.getId(), object.getName());
    }
}
