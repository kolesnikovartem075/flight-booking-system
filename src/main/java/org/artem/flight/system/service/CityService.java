package org.artem.flight.system.service;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.database.entity.City;
import org.artem.flight.system.database.repository.CityRepository;
import org.artem.flight.system.dto.AirportCreateEditDto;
import org.artem.flight.system.dto.CityCreateEditDto;
import org.artem.flight.system.dto.CityReadDto;
import org.artem.flight.system.mapper.city.CityCreateEditMapper;
import org.artem.flight.system.mapper.city.CityReadMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CityService {

    private final CityRepository cityRepository;
    private final CityReadMapper cityReadMapper;
    private final CityCreateEditMapper cityCreateEditMapper;

    public List<CityReadDto> findAll() {
        return cityRepository.findAll().stream()
                .map(cityReadMapper::map)
                .toList();
    }

    public Optional<CityReadDto> findById(Long id) {
        return cityRepository.findById(id)
                .map(cityReadMapper::map);
    }

    @Transactional
    public CityReadDto create(CityCreateEditDto addressDto) {
        return Optional.of(addressDto)
                .map(cityCreateEditMapper::map)
                .map(cityRepository::save)
                .map(cityReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public City createForAirport(AirportCreateEditDto airportDto) {
        return Optional.of(airportDto)
                .map(cityCreateEditMapper::map)
                .map(cityCreateEditMapper::map)
                .map(cityRepository::saveAndFlush)
                .orElseThrow();
    }

    @Transactional
    public Optional<CityReadDto> update(Long id, CityCreateEditDto addressDto) {
        return cityRepository.findById(id)
                .map(entity -> cityCreateEditMapper.map(addressDto, entity))
                .map(cityRepository::saveAndFlush)
                .map(cityReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return cityRepository.findById(id)
                .map(entity -> {
                    cityRepository.delete(entity);
                    cityRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
