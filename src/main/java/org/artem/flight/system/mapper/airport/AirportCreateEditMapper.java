package org.artem.flight.system.mapper.airport;

import lombok.AllArgsConstructor;
import org.artem.flight.system.database.entity.Address;
import org.artem.flight.system.database.entity.Airport;
import org.artem.flight.system.database.repository.AddressRepository;
import org.artem.flight.system.dto.AirportCreateEditDto;
import org.artem.flight.system.mapper.Mapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AirportCreateEditMapper implements Mapper<AirportCreateEditDto, Airport> {

    private final AddressRepository addressRepository;

    @Override
    public Airport map(AirportCreateEditDto object) {
        var airport = new Airport();
        copy(object, airport);

        return airport;
    }

    @Override
    public Airport map(AirportCreateEditDto fromObject, Airport toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(AirportCreateEditDto fromObject, Airport toObject) {
        var address = getAddress(fromObject);

        toObject.setName(fromObject.getName());
        toObject.setAddress(address);
    }

    private Address getAddress(AirportCreateEditDto fromObject) {
        return addressRepository.findById(fromObject.getAddressId())
                .orElseThrow();
    }
}