package org.artem.flight.system.dto;

import lombok.Value;

@Value
public class SeatCreateEditDto {


    Long flightId;
    String numberNo;
    String rank;
}