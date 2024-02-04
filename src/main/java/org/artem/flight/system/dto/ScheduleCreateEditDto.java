package org.artem.flight.system.dto;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Value
public class ScheduleCreateEditDto {


    Long flightId;
    Long startAirportId;
    Long endAirportId;
    LocalDateTime startTime;
    LocalDateTime endTime;
    String status;
    List<ReservationSeatEditDto> reservations = new ArrayList<>();
}