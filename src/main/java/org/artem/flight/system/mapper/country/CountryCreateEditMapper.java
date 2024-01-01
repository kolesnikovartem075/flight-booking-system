package org.artem.flight.system.mapper.country;

import lombok.AllArgsConstructor;
import org.artem.flight.system.database.entity.City;
import org.artem.flight.system.database.entity.Country;
import org.artem.flight.system.database.repository.CountryRepository;
import org.artem.flight.system.dto.CityCreateEditDto;
import org.artem.flight.system.dto.CountryCreateEditDto;
import org.artem.flight.system.mapper.Mapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CountryCreateEditMapper implements Mapper<CountryCreateEditDto, Country> {

    public Country map(CountryCreateEditDto object) {
        var country = new Country();
        copy(object, country);

        return country;
    }

    public Country map(CountryCreateEditDto fromObject, Country toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(CountryCreateEditDto fromObject, Country target) {
        target.setName(fromObject.getName());
    }
}