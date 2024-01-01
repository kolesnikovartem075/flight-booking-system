package org.artem.flight.system.dto;

import lombok.Value;

@Value
public class CityReadDto {


    Long id;
    String name;
    CountryReadDto country;
}