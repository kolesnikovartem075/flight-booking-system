package org.artem.flight.system.mapper;

import org.artem.flight.system.database.entity.Customer;
import org.artem.flight.system.dto.CustomerCreateEditDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerCreateEditMapper {

    Customer map(CustomerCreateEditDto object);

    Customer map(CustomerCreateEditDto fromObject, Customer toObject);
}