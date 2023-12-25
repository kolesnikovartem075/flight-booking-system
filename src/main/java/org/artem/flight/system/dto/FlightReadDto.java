package org.artem.flight.system.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class FlightReadDto {


    Long id;
    String flightNo;
    AirportReadDto airport;
    AirlineReadDto airline;
    List<SeatReadDto> seats;
    Integer seatCapacity;
}