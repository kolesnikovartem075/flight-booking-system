package org.artem.flight.system.dto;

import lombok.Value;

@Value
public class CityCreateEditDto {


    String name;
    Long countryId;
}