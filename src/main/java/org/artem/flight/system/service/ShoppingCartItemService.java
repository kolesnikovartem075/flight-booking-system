package org.artem.flight.system.service;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.database.repository.ShoppingCartItemRepository;
import org.artem.flight.system.dto.ShoppingCartItemCreateEditDto;
import org.artem.flight.system.dto.ShoppingCartItemReadDto;
import org.artem.flight.system.mapper.ShoppingCartItemCreateEditMapper;
import org.artem.flight.system.mapper.ShoppingCartItemReadMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShoppingCartItemService {

    private final ShoppingCartItemRepository shoppingCartItemRepository;
    private final ShoppingCartItemReadMapper shoppingCartItemReadMapper;
    private final ShoppingCartItemCreateEditMapper shoppingCartItemCreateEditMapper;

    public List<ShoppingCartItemReadDto> findAll() {
        return shoppingCartItemRepository.findAll().stream()
                .map(shoppingCartItemReadMapper::map)
                .toList();
    }

    public Optional<ShoppingCartItemReadDto> findById(Long id) {
        return shoppingCartItemRepository.findById(id)
                .map(shoppingCartItemReadMapper::map);
    }


    @Transactional
    public ShoppingCartItemReadDto create(ShoppingCartItemCreateEditDto shoppingCartItemCreateEditDto) {
        return Optional.of(shoppingCartItemCreateEditDto)
                .map(shoppingCartItemCreateEditMapper::map)
                .map(shoppingCartItemRepository::save)
                .map(shoppingCartItemReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<ShoppingCartItemReadDto> update(Long id, ShoppingCartItemCreateEditDto shoppingCartDto) {
        return shoppingCartItemRepository.findById(id)
                .map(entity -> shoppingCartItemCreateEditMapper.map(shoppingCartDto, entity))
                .map(shoppingCartItemRepository::saveAndFlush)
                .map(shoppingCartItemReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return shoppingCartItemRepository.findById(id)
                .map(entity -> {
                    shoppingCartItemRepository.delete(entity);
                    shoppingCartItemRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}