package org.artem.flight.system.mapper.city;

import lombok.AllArgsConstructor;
import org.artem.flight.system.database.entity.City;
import org.artem.flight.system.dto.CityReadDto;
import org.artem.flight.system.dto.CountryReadDto;
import org.artem.flight.system.mapper.Mapper;
import org.artem.flight.system.mapper.country.CountryReadMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CityReadMapper implements Mapper<City, CityReadDto> {

    private final CountryReadMapper countryReadMapper;

    public CityReadDto map(City object) {
        var country = countryReadMapper.map(object.getCountry());

        return new CityReadDto(object.getId(), object.getName(), country);
    }
}
