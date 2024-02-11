package org.artem.flight.system.dto;

import lombok.Value;
import org.artem.flight.system.database.entity.OrderStatus;

import java.time.LocalDate;
import java.util.List;

@Value
public class OrderReadDto {

    Long id;
    OrderStatus orderStatus;
    CustomerReadDto customer;
    Integer orderTotal;
    List<OrderLineReadDto> orderLines;

    LocalDate dateCreated;
}