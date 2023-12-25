package org.artem.flight.system.dto;

import lombok.Value;

import java.util.List;

@Value
public class FlightReadDto {


    Long id;
    String flightNo;
    AirportReadDto airport;
    AirlineReadDto airline;
    List<SeatReadDto> seats;
    Long seatCapacity;
}