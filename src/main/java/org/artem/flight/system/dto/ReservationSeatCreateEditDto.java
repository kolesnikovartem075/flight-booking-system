package org.artem.flight.system.dto;

import lombok.Value;

@Value
public class ReservationSeatCreateEditDto {


    Long scheduleId;
    Long seatId;
    Long userId;
    Integer price;
}