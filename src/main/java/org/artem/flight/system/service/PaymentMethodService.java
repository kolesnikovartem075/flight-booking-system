package org.artem.flight.system.service;


import lombok.RequiredArgsConstructor;
import org.artem.flight.system.database.repository.PaymentMethodRepository;
import org.artem.flight.system.dto.PaymentMethodCreateEditDto;
import org.artem.flight.system.dto.PaymentMethodReadDto;
import org.artem.flight.system.mapper.PaymentMethodCreateEditMapper;
import org.artem.flight.system.mapper.PaymentMethodReadMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentMethodService {

    private final PaymentMethodReadMapper paymentMethodReadMapper = Mappers.getMapper(PaymentMethodReadMapper.class);
    private final PaymentMethodCreateEditMapper paymentMethodCreateEditMapper;
    private final PaymentMethodRepository paymentMethodRepository;

    public Optional<PaymentMethodReadDto> findByCustomerId(Long costumerId) {
        return paymentMethodRepository.findByCustomerId(costumerId)
                .map(paymentMethodReadMapper::map);
    }

    @Transactional
    public PaymentMethodReadDto create(PaymentMethodCreateEditDto paymentMethod) {
        return Optional.of(paymentMethod)
                .map(paymentMethodCreateEditMapper::map)
                .map(paymentMethodRepository::save)
                .map(paymentMethodReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<PaymentMethodReadDto> update(PaymentMethodCreateEditDto paymentMethod, Long id) {
        return paymentMethodRepository.findById(id)
                .map(entity -> paymentMethodCreateEditMapper.map(paymentMethod, entity))
                .map(paymentMethodRepository::saveAndFlush)
                .map(paymentMethodReadMapper::map);
    }
}