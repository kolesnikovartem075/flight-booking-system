package org.artem.flight.system.mapper;

import org.artem.flight.system.database.entity.Customer;
import org.artem.flight.system.dto.CustomerReadDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerReadMapper {
    CustomerReadDto map(Customer customer);
}