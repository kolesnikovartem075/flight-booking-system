package org.artem.flight.system.mapper;

import org.artem.flight.system.database.entity.PaymentMethod;
import org.artem.flight.system.dto.PaymentMethodReadDto;
import org.mapstruct.Mapper;

@Mapper
public interface PaymentMethodReadMapper {
    PaymentMethodReadDto map(PaymentMethod paymentMethod);
}