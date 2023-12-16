package org.artem.flight.system.dto;

import lombok.Value;

@Value
public class AirportCreateEditDto {


    String name;
    AddressCreateEditDto addressDto;
}