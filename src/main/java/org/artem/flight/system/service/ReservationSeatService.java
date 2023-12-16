package org.artem.flight.system.service;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.database.repository.ReservationSeatRepository;
import org.artem.flight.system.dto.ReservationSeatCreateEditDto;
import org.artem.flight.system.dto.ReservationSeatReadDto;
import org.artem.flight.system.mapper.reservation.ReservationSeatCreateEditMapper;
import org.artem.flight.system.mapper.reservation.ReservationSeatReadMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationSeatService {

    private final ReservationSeatRepository reservationRepository;
    private final ReservationSeatReadMapper reservationReadMapper;
    private final ReservationSeatCreateEditMapper reservationCreateEditMapper;

    public List<ReservationSeatReadDto> findAll() {
        return reservationRepository.findAll().stream()
                .map(reservationReadMapper::map)
                .toList();
    }

    public Optional<ReservationSeatReadDto> findById(Long id) {
        return reservationRepository.findById(id)
                .map(reservationReadMapper::map);
    }

    @Transactional
    public ReservationSeatReadDto create(ReservationSeatCreateEditDto reservationDto) {
        return Optional.of(reservationDto)
                .map(reservationCreateEditMapper::map)
                .map(reservationRepository::save)
                .map(reservationReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<ReservationSeatReadDto> update(Long id, ReservationSeatCreateEditDto reservationDto) {
        return reservationRepository.findById(id)
                .map(entity -> reservationCreateEditMapper.map(reservationDto, entity))
                .map(reservationRepository::saveAndFlush)
                .map(reservationReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return reservationRepository.findById(id)
                .map(entity -> {
                    reservationRepository.delete(entity);
                    reservationRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
