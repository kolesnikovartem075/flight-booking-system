package org.artem.flight.system.dto;

import lombok.Value;

@Value
public class AirportReadDto {


    Long id;

    String name;
    AddressReadDto addressDto;
}