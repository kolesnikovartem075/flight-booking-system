package org.artem.flight.system.mapper.airport;

import lombok.AllArgsConstructor;
import org.artem.flight.system.database.entity.Airport;
import org.artem.flight.system.dto.AddressReadDto;
import org.artem.flight.system.dto.AirportReadDto;
import org.artem.flight.system.mapper.Mapper;
import org.artem.flight.system.mapper.address.AddressReadMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AirportReadMapper implements Mapper<Airport, AirportReadDto> {

    private final AddressReadMapper addressReadMapper;

    @Override
    public AirportReadDto map(Airport object) {
        var address = getAddressReadDto(object);

        return new AirportReadDto(object.getId(), object.getName(), address);
    }

    private AddressReadDto getAddressReadDto(Airport object) {
        return addressReadMapper.map(object.getAddress());
    }
}