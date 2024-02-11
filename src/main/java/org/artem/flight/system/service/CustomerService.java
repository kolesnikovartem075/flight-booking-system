package org.artem.flight.system.service;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.database.repository.CustomerRepository;
import org.artem.flight.system.dto.CustomerCreateEditDto;
import org.artem.flight.system.dto.CustomerReadDto;
import org.artem.flight.system.mapper.CustomerCreateEditMapper;
import org.artem.flight.system.mapper.CustomerReadMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerReadMapper customerReadMapper = Mappers.getMapper(CustomerReadMapper.class);
    private final CustomerCreateEditMapper customerCreateEditMapper;

    public List<CustomerReadDto> findAll() {
        return customerRepository.findAll().stream()
                .map(customerReadMapper::map)
                .toList();
    }

    public Optional<CustomerReadDto> findById(Long id) {
        return customerRepository.findById(id)
                .map(customerReadMapper::map);
    }

    public Optional<CustomerReadDto> findByEmail(String email) {
        return customerRepository.findByEmail(email)
                .map(customerReadMapper::map);
    }

    @Transactional
    public CustomerReadDto create(CustomerCreateEditDto customerDto) {
        return Optional.of(customerDto)
                .map(customerCreateEditMapper::map)
                .map(customerRepository::save)
                .map(customerReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<CustomerReadDto> update(Long id, CustomerCreateEditDto customerDto) {
        return customerRepository.findById(id)
                .map(entity -> customerCreateEditMapper.map(customerDto, entity))
                .map(customerRepository::saveAndFlush)
                .map(customerReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return customerRepository.findById(id)
                .map(entity -> {
                    customerRepository.delete(entity);
                    customerRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
