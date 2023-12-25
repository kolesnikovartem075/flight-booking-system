package org.artem.flight.system.dto;

import lombok.Value;

@Value
public class ReservationSeatReadDto {


    Long id;

    SeatReadDto seat;
    String status;
    Integer price;
}