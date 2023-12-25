package org.artem.flight.system.mapper.flight;

import lombok.AllArgsConstructor;
import org.artem.flight.system.database.entity.Airline;
import org.artem.flight.system.database.entity.Airport;
import org.artem.flight.system.database.entity.Flight;
import org.artem.flight.system.database.repository.AirlineRepository;
import org.artem.flight.system.database.repository.AirportRepository;
import org.artem.flight.system.dto.FlightCreateEditDto;
import org.artem.flight.system.mapper.Mapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class FlightCreateEditMapper implements Mapper<FlightCreateEditDto, Flight> {


    private final AirlineRepository airlineRepository;
    private final AirportRepository airportRepository;

    public Flight map(FlightCreateEditDto object) {
        var flight = new Flight();
        copy(object, flight);

        return flight;
    }

    public Flight map(FlightCreateEditDto fromObject, Flight toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(FlightCreateEditDto fromObject, Flight target) {
        var airline = findAirline(fromObject);
        var airport = findAirport(fromObject);

        target.setFlightNo(fromObject.getFlightNo());
        target.setAirline(airline);
        target.setAirport(airport);
    }

    private Airport findAirport(FlightCreateEditDto fromObject) {
        return airportRepository.findById(fromObject.getAirportId())
                .orElseThrow();
    }

    private Airline findAirline(FlightCreateEditDto fromObject) {
        return airlineRepository.findById(fromObject.getAirlineId())
                .orElseThrow();
    }
}