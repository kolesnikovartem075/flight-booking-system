package org.artem.flight.system.service;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.database.repository.AirlineRepository;
import org.artem.flight.system.dto.AirlineCreateEditDto;
import org.artem.flight.system.dto.AirlineReadDto;
import org.artem.flight.system.mapper.airline.AirlineCreateEditMapper;
import org.artem.flight.system.mapper.airline.AirlineReadMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AirlineService {

    private final AirlineRepository airlineRepository;
    private final AirlineReadMapper airlineReadMapper;
    private final AirlineCreateEditMapper airlineCreateEditMapper;

    public List<AirlineReadDto> findAll() {
        return airlineRepository.findAll().stream()
                .map(airlineReadMapper::map)
                .toList();
    }

    public Optional<AirlineReadDto> findById(Long id) {
        return airlineRepository.findById(id)
                .map(airlineReadMapper::map);
    }

    @Transactional
    public AirlineReadDto create(AirlineCreateEditDto airlineDto) {
        return Optional.of(airlineDto)
                .map(airlineCreateEditMapper::map)
                .map(airlineRepository::save)
                .map(airlineReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<AirlineReadDto> update(Long id, AirlineCreateEditDto airlineDto) {
        return airlineRepository.findById(id)
                .map(entity -> airlineCreateEditMapper.map(airlineDto, entity))
                .map(airlineRepository::saveAndFlush)
                .map(airlineReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return airlineRepository.findById(id)
                .map(entity -> {
                    airlineRepository.delete(entity);
                    airlineRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
