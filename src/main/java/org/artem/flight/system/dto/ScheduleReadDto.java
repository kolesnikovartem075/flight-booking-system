package org.artem.flight.system.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Value
public class ScheduleReadDto {


    Long id;
    FlightReadDto flight;
    AirportReadDto start;
    AirportReadDto destination;
    List<ReservationSeatReadDto> reservationSeats;
    LocalDateTime startTime;
    LocalDateTime endTime;
    String status;

}