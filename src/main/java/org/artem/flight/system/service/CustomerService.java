package org.artem.flight.system.service;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.database.repository.CustomerRepository;
import org.artem.flight.system.dto.CustomerReadDto;
import org.artem.flight.system.http.controller.CustomerRegistrationCreateEditDto;
import org.artem.flight.system.mapper.CustomerCreateEditMapper;
import org.artem.flight.system.mapper.CustomerReadMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerReadMapper customerReadMapper = Mappers.getMapper(CustomerReadMapper.class);
    private final CustomerCreateEditMapper customerCreateEditMapper;

    @Transactional
    public CustomerReadDto create(CustomerRegistrationCreateEditDto customerDto) {
        return Optional.of(customerDto)
                .map(customerCreateEditMapper::map)
                .map(customerRepository::save)
                .map(customerReadMapper::map)
                .orElseThrow();
    }

}
