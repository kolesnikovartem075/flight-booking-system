package org.artem.flight.system.service;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.database.repository.AirportRepository;
import org.artem.flight.system.dto.AirportCreateEditDto;
import org.artem.flight.system.dto.AirportReadDto;
import org.artem.flight.system.mapper.airport.AirportCreateEditMapper;
import org.artem.flight.system.mapper.airport.AirportReadMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AirportService {


    private final AirportRepository airportRepository;
    private final AirportReadMapper airportReadMapper;
    private final AirportCreateEditMapper airportCreateEditMapper;

    public List<AirportReadDto> findAll() {
        return airportRepository.findAll().stream()
                .map(airportReadMapper::map)
                .toList();
    }

    public Optional<AirportReadDto> findById(Long id) {
        return airportRepository.findById(id)
                .map(airportReadMapper::map);
    }

    @Transactional
    public AirportReadDto create(AirportCreateEditDto airportDto) {
        return Optional.of(airportDto)
                .map(airportCreateEditMapper::map)
                .map(airportRepository::save)
                .map(airportReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<AirportReadDto> update(Long id, AirportCreateEditDto airportDto) {
        return airportRepository.findById(id)
                .map(entity -> airportCreateEditMapper.map(airportDto, entity))
                .map(airportRepository::saveAndFlush)
                .map(airportReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return airportRepository.findById(id)
                .map(entity -> {
                    airportRepository.delete(entity);
                    airportRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
