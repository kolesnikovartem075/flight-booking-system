package org.artem.flight.system.dto;

import lombok.Data;

@Data
public class SeatCreateEditDto {


    Long flightId;
    String numberNo;
    String rank;
}