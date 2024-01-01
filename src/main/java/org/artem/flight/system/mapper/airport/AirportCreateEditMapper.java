package org.artem.flight.system.mapper.airport;

import lombok.AllArgsConstructor;
import org.artem.flight.system.database.entity.Airport;
import org.artem.flight.system.database.entity.City;
import org.artem.flight.system.database.entity.Country;
import org.artem.flight.system.database.repository.CityRepository;
import org.artem.flight.system.database.repository.CountryRepository;
import org.artem.flight.system.dto.AirportCreateEditDto;
import org.artem.flight.system.dto.CityCreateEditDto;
import org.artem.flight.system.mapper.Mapper;
import org.artem.flight.system.service.CityService;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AirportCreateEditMapper implements Mapper<AirportCreateEditDto, Airport> {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final CityService cityService;

    @Override
    public Airport map(AirportCreateEditDto object) {
        var city = createCity(object);

        var airport = new Airport();
        airport.setName(object.getName());
        airport.setCity(city);
        return airport;
    }

    public Airport map(City city, String name) {
        var airport = new Airport();
        airport.setName(city.getName());
        airport.setCity(city);
        return airport;
    }

    private City createCity(AirportCreateEditDto object) {
        var cityCreateDto = new CityCreateEditDto(object.getCityName(), object.getCountryId());
        var city = cityService.create(cityCreateDto);
        cityRepository.flush();
        return cityRepository.findById(city.getId())
                .orElseThrow();
    }

    @Override
    public Airport map(AirportCreateEditDto fromObject, Airport toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(AirportCreateEditDto fromObject, Airport toObject) {
        var country = getCountry(fromObject);
        var city = getCity(toObject);
        city.setName(fromObject.getCityName());
        city.setCountry(country);

        toObject.setName(fromObject.getName());
        toObject.setCity(city);
    }

    private static City getCity(Airport toObject) {
        return toObject.getCity();
    }

    private Country getCountry(AirportCreateEditDto fromObject) {
        return countryRepository.findById(fromObject.getCountryId()).orElseThrow();
    }
}