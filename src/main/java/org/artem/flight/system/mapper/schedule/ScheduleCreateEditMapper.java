package org.artem.flight.system.mapper.schedule;

import lombok.AllArgsConstructor;
import org.artem.flight.system.database.entity.Airport;
import org.artem.flight.system.database.entity.Flight;
import org.artem.flight.system.database.entity.Schedule;
import org.artem.flight.system.database.entity.ScheduleStatus;
import org.artem.flight.system.database.repository.AirportRepository;
import org.artem.flight.system.database.repository.FlightRepository;
import org.artem.flight.system.database.repository.ReservationSeatRepository;
import org.artem.flight.system.dto.ReservationSeatCreateEditDto;
import org.artem.flight.system.dto.ReservationSeatReadDto;
import org.artem.flight.system.dto.ScheduleCreateEditDto;
import org.artem.flight.system.mapper.Mapper;
import org.artem.flight.system.service.ReservationSeatService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class ScheduleCreateEditMapper implements Mapper<ScheduleCreateEditDto, Schedule> {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final ReservationSeatRepository reservationSeatRepository;
    private final ReservationSeatService reservationSeatService;


    public Schedule map(ScheduleCreateEditDto object) {
        var schedule = new Schedule();
        copy(object, schedule);

        return schedule;
    }

    public Schedule map(ScheduleCreateEditDto fromObject, Schedule toObject) {
        copy(fromObject, toObject);
        var reservations = updateReservations(fromObject);
        return toObject;
    }

    private void copy(ScheduleCreateEditDto fromObject, Schedule toObject) {
        var flight = getFlight(fromObject);
        var startAirport = getAirport(fromObject.getStartAirportId());
        var endAirport = getAirport(fromObject.getEndAirportId());

        toObject.setFlight(flight);
        toObject.setStart(startAirport);
        toObject.setDestination(endAirport);
        toObject.setStartTime(fromObject.getStartTime());
        toObject.setEndTime(fromObject.getEndTime());
        toObject.setStatus(ScheduleStatus.valueOf(fromObject.getStatus()));
    }

    private Airport getAirport(Long airportId) {
        return airportRepository.findById(airportId).orElseThrow();
    }

    private Flight getFlight(ScheduleCreateEditDto fromObject) {
        return flightRepository.findById(fromObject.getFlightId()).orElseThrow();
    }

    private List<ReservationSeatReadDto> updateReservations(ScheduleCreateEditDto fromObject) {
        return fromObject.getReservations().stream()
                .map(entity -> reservationSeatService.update(entity.getId(), entity))
                .map(Optional::orElseThrow)
                .toList();
    }
}