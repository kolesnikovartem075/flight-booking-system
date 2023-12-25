package org.artem.flight.system.dto;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class ScheduleCreateEditDto {


    Long flightId;
    Long startAirportId;
    Long endAirportId;
    LocalDateTime startTime;
    LocalDateTime endTime;
    String status;
}