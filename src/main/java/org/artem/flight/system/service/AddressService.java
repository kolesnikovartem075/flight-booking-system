package org.artem.flight.system.service;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.database.repository.AddressRepository;
import org.artem.flight.system.dto.AddressCreateEditDto;
import org.artem.flight.system.mapper.address.AddressCreateEditMapper;
import org.artem.flight.system.dto.AddressReadDto;
import org.artem.flight.system.mapper.address.AddressReadMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressReadMapper addressReadMapper;
    private final AddressCreateEditMapper addressCreateEditMapper;

    public List<AddressReadDto> findAll() {
        return addressRepository.findAll().stream()
                .map(addressReadMapper::map)
                .toList();
    }

    public Optional<AddressReadDto> findById(Long id) {
        return addressRepository.findById(id)
                .map(addressReadMapper::map);
    }

    @Transactional
    public AddressReadDto create(AddressCreateEditDto addressDto) {
        return Optional.of(addressDto)
                .map(addressCreateEditMapper::map)
                .map(addressRepository::save)
                .map(addressReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<AddressReadDto> update(Long id, AddressCreateEditDto addressDto) {
        return addressRepository.findById(id)
                .map(entity -> addressCreateEditMapper.map(addressDto, entity))
                .map(addressRepository::saveAndFlush)
                .map(addressReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return addressRepository.findById(id)
                .map(entity -> {
                    addressRepository.delete(entity);
                    addressRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
