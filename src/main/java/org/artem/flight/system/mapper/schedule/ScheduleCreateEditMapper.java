package org.artem.flight.system.mapper.schedule;

import lombok.AllArgsConstructor;
import org.artem.flight.system.database.entity.Airport;
import org.artem.flight.system.database.entity.Flight;
import org.artem.flight.system.database.entity.Schedule;
import org.artem.flight.system.database.repository.AirportRepository;
import org.artem.flight.system.database.repository.FlightRepository;
import org.artem.flight.system.dto.ScheduleCreateEditDto;
import org.artem.flight.system.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class ScheduleCreateEditMapper implements Mapper<ScheduleCreateEditDto, Schedule> {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;


    public Schedule map(ScheduleCreateEditDto object) {
        var schedule = new Schedule();
        copy(object, schedule);

        return schedule;
    }

    public Schedule map(ScheduleCreateEditDto fromObject, Schedule toObject) {
        copy(fromObject, toObject);
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
        toObject.setStatus(fromObject.getStatus());
    }

    private Airport getAirport(Long airportId) {
        return airportRepository.findById(airportId).orElseThrow();
    }

    private Flight getFlight(ScheduleCreateEditDto fromObject) {
        return flightRepository.findById(fromObject.getFlightId()).orElseThrow();
    }
}