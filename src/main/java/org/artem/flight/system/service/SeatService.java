package org.artem.flight.system.service;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.database.repository.SeatRepository;
import org.artem.flight.system.dto.SeatCreateEditDto;
import org.artem.flight.system.dto.SeatReadDto;
import org.artem.flight.system.mapper.seat.SeatCreateEditMapper;
import org.artem.flight.system.mapper.seat.SeatReadMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SeatService {


    private final SeatRepository seatRepository;
    private final SeatReadMapper seatReadMapper;
    private final SeatCreateEditMapper seatCreateEditMapper;

    public List<SeatReadDto> findAll() {
        return seatRepository.findAll().stream()
                .map(seatReadMapper::map)
                .toList();
    }

    public Optional<SeatReadDto> findById(Long id) {
        return seatRepository.findById(id)
                .map(seatReadMapper::map);
    }

    @Transactional
    public SeatReadDto create(SeatCreateEditDto seatDto) {
        return Optional.of(seatDto)
                .map(seatCreateEditMapper::map)
                .map(seatRepository::save)
                .map(seatReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Integer create(List<SeatCreateEditDto> seatDto) {
        return Math.toIntExact(seatDto.stream()
                .map(this::create)
                .count());
    }

    @Transactional
    public Optional<SeatReadDto> update(Long id, SeatCreateEditDto seatDto) {
        return seatRepository.findById(id)
                .map(entity -> seatCreateEditMapper.map(seatDto, entity))
                .map(seatRepository::saveAndFlush)
                .map(seatReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return seatRepository.findById(id)
                .map(entity -> {
                    seatRepository.delete(entity);
                    seatRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}