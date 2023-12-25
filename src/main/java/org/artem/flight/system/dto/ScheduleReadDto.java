package org.artem.flight.system.dto;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class ScheduleReadDto {


    Long id;
    FlightReadDto flight;
    AirportReadDto start;
    AirportReadDto destination;
    LocalDateTime startTime;
    LocalDateTime endTime;
    String status;

}