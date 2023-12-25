package org.artem.flight.system.dto;

import lombok.Value;

@Value
public class FlightCreateEditDto {


    String flightNo;
    Long airportId;
    Long airlineId;
}