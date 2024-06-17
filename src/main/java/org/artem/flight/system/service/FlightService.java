package org.artem.flight.system.service;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.database.entity.Flight;
import org.artem.flight.system.database.repository.FlightRepository;
import org.artem.flight.system.dto.FlightCreateEditDto;
import org.artem.flight.system.dto.FlightReadDto;
import org.artem.flight.system.mapper.flight.FlightCreateEditMapper;
import org.artem.flight.system.mapper.flight.FlightReadMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightReadMapper flightReadMapper;
    private final FlightCreateEditMapper flightCreateEditMapper;

    public List<FlightReadDto> findAll() {
        return flightRepository.findAll().stream()
                .map(flightReadMapper::map)
                .toList();
    }

    public Optional<FlightReadDto> findById(Long id) {
        return flightRepository.findById(id)
                .map(flightReadMapper::map);
    }

    @Transactional
    public FlightReadDto create(FlightCreateEditDto flightDto) {
        return Optional.of(flightDto)
                .map(flightCreateEditMapper::map)
                .map(flightRepository::save)
                .map(flightReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<FlightReadDto> update(Long id, FlightCreateEditDto flightDto) {
        return flightRepository.findById(id)
                .map(entity -> flightCreateEditMapper.map(flightDto, entity))
                .map(flightRepository::saveAndFlush)
                .map(flightReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return flightRepository.findById(id)
                .map(entity -> {
                    flightRepository.delete(entity);
                    flightRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}