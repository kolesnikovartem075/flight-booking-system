package org.artem.flight.system.dto;

import lombok.Value;

@Value
public class ReservationSeatReadDto {


    Long id;
    String status;
    Integer price;
}