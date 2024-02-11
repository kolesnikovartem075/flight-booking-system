package org.artem.flight.system.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.artem.flight.system.database.repository.ShoppingCartRepository;
import org.artem.flight.system.dto.*;
import org.artem.flight.system.mapper.ShoppingCartCreateEditMapper;
import org.artem.flight.system.mapper.ShoppingCartReadMapper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Log4j2
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartItemService shoppingCartItemService;
    private final ShoppingCartReadMapper shoppingCartReadMapper;
    private final ShoppingCartCreateEditMapper shoppingCartCreateEditMapper;

    public List<ShoppingCartReadDto> findAll() {
        return shoppingCartRepository.findAll().stream()
                .map(shoppingCartReadMapper::map)
                .toList();
    }

    public Optional<ShoppingCartReadDto> findById(Long id) {
        return shoppingCartRepository.findById(id)
                .map(shoppingCartReadMapper::map);
    }

    public Optional<ShoppingCartReadDto> findBy(String sessionId) {
        return shoppingCartRepository.findBySessionId(sessionId)
                .map(shoppingCartReadMapper::map);
    }

    @Transactional
    public ShoppingCartReadDto create(ShoppingCartCreateEditDto customerDto) {
        return Optional.of(customerDto)
                .map(shoppingCartCreateEditMapper::map)
                .map(shoppingCartRepository::save)
                .map(shoppingCartReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<ShoppingCartReadDto> update(String sessionId, ShoppingCartCreateEditDto customerDto) {
        return shoppingCartRepository.findBySessionId(sessionId)
                .map(entity -> shoppingCartCreateEditMapper.map(customerDto, entity))
                .map(shoppingCartRepository::saveAndFlush)
                .map(shoppingCartReadMapper::map);
    }

    @Transactional
    public boolean delete(String sessionId) {
        return shoppingCartRepository.findBySessionId(sessionId)
                .map(entity -> {
                    shoppingCartRepository.delete(entity);
                    shoppingCartRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public boolean delete(Long id) {
        return shoppingCartRepository.findById(id)
                .map(entity -> {
                    shoppingCartRepository.delete(entity);
                    shoppingCartRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public void putItem(Long productId, String sessionId) {
        ShoppingCartReadDto shoppingCart = getShoppingCart(sessionId);
        if (!isPresent(productId, shoppingCart)) {
            log.error("Trying to put the same item into shopping cart");
            return;
        }

        ShoppingCartItemCreateEditDto shoppingCartItemCreateEditDto = new ShoppingCartItemCreateEditDto(shoppingCart.getId(), productId);
        shoppingCartItemService.create(shoppingCartItemCreateEditDto);
    }

    private boolean isPresent(Long productId, ShoppingCartReadDto shoppingCart) {
        return shoppingCart.getItems().stream()
                .map(ShoppingCartItemReadDto::getReservationSeat)
                .map(ReservationSeatReadDto::getId)
                .filter(id -> Objects.equals(id, productId))
                .toList()
                .isEmpty();
    }

    private ShoppingCartReadDto getShoppingCart(String sessionId) {
        return findBy(sessionId)
                .orElseGet(() -> create(new ShoppingCartCreateEditDto(sessionId)));
    }
}