package org.artem.flight.system.service;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.database.repository.CountryRepository;
import org.artem.flight.system.dto.CountryCreateEditDto;
import org.artem.flight.system.dto.CountryReadDto;
import org.artem.flight.system.mapper.country.CountryCreateEditMapper;
import org.artem.flight.system.mapper.country.CountryReadMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CountryService {

    private final CountryRepository countryRepository;
    private final CountryReadMapper countryReadMapper;
    private final CountryCreateEditMapper countryCreateEditMapper;

    public List<CountryReadDto> findAll() {
        return countryRepository.findAll().stream()
                .map(countryReadMapper::map)
                .toList();
    }

    public Optional<CountryReadDto> findById(Long id) {
        return countryRepository.findById(id)
                .map(countryReadMapper::map);
    }

    @Transactional
    public CountryReadDto create(CountryCreateEditDto addressDto) {
        return Optional.of(addressDto)
                .map(countryCreateEditMapper::map)
                .map(countryRepository::save)
                .map(countryReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<CountryReadDto> update(Long id, CountryCreateEditDto addressDto) {
        return countryRepository.findById(id)
                .map(entity -> countryCreateEditMapper.map(addressDto, entity))
                .map(countryRepository::saveAndFlush)
                .map(countryReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return countryRepository.findById(id)
                .map(entity -> {
                    countryRepository.delete(entity);
                    countryRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
