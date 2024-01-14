package org.artem.flight.system.dto;

import lombok.Value;

import java.util.List;

@Value
public class FlightCreateEditDto {


    String flightNo;
    Long airlineId;
    List<SeatCreateEditDto> seats;
}