package org.artem.flight.system.mapper.address;

import org.artem.flight.system.database.entity.Address;
import org.artem.flight.system.dto.AddressReadDto;
import org.artem.flight.system.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AddressReadMapper implements Mapper<Address, AddressReadDto> {
    public AddressReadDto map(Address object) {
        return new AddressReadDto(object.getId(), object.getName());
    }
}
