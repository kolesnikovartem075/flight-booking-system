package org.artem.flight.system.mapper.city;

import lombok.AllArgsConstructor;
import org.artem.flight.system.database.entity.City;
import org.artem.flight.system.database.entity.Country;
import org.artem.flight.system.database.repository.CountryRepository;
import org.artem.flight.system.dto.AirportCreateEditDto;
import org.artem.flight.system.dto.CityCreateEditDto;
import org.artem.flight.system.mapper.Mapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CityCreateEditMapper implements Mapper<CityCreateEditDto, City> {

    private final CountryRepository countryRepository;

    public City map(CityCreateEditDto object) {
        var address = new City();
        copy(object, address);

        return address;
    }

    public CityCreateEditDto map(AirportCreateEditDto object) {
        return new CityCreateEditDto(object.getCityName(), object.getCountryId());
    }

    public City map(CityCreateEditDto fromObject, City toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(CityCreateEditDto fromObject, City target) {
        var country = getCountry(fromObject);

        target.setName(fromObject.getName());
        target.setCountry(country);
    }

    private Country getCountry(CityCreateEditDto fromObject) {
        return countryRepository.findById(fromObject.getCountryId()).orElseThrow();
    }
}