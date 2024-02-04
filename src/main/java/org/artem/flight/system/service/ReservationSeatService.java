package org.artem.flight.system.service;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.database.entity.ReservationSeat;
import org.artem.flight.system.database.entity.ReservationStatus;
import org.artem.flight.system.database.entity.Schedule;
import org.artem.flight.system.database.entity.Seat;
import org.artem.flight.system.database.repository.ReservationSeatRepository;
import org.artem.flight.system.database.repository.ScheduleRepository;
import org.artem.flight.system.dto.ReservationSeatCreateEditDto;
import org.artem.flight.system.dto.ReservationSeatEditDto;
import org.artem.flight.system.dto.ReservationSeatReadDto;
import org.artem.flight.system.mapper.reservation.ReservationSeatCreateEditMapper;
import org.artem.flight.system.mapper.reservation.ReservationSeatEditMapper;
import org.artem.flight.system.mapper.reservation.ReservationSeatReadMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationSeatService {

    private final ReservationSeatRepository reservationRepository;
    private final ReservationSeatReadMapper reservationReadMapper;
    private final ReservationSeatCreateEditMapper reservationCreateEditMapper;
    private final ReservationSeatEditMapper reservationSeatEditMapper;
    private final ScheduleRepository scheduleRepository;

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
    public List<ReservationSeatReadDto> create(ReservationSeatCreateEditDto reservationDto) {
        var schedule = scheduleRepository.findById(reservationDto.getScheduleId()).orElseThrow();
        var seats = schedule.getFlight().getSeats();
        var reservationSeats = getReservedSeatIds(schedule);


        return seats.stream()
                .filter(it -> !reservationSeats.contains(it.getId()))
                .map(it -> new ReservationSeatCreateEditDto(reservationDto.getScheduleId(), it.getId(), null, null, ReservationStatus.FREE.name()))
                .map(reservationCreateEditMapper::map)
                .map(reservationRepository::save)
                .map(reservationReadMapper::map)
                .toList();
    }

    @Transactional
    public Optional<ReservationSeatReadDto> update(Long id, ReservationSeatEditDto reservationDto) {
        return reservationRepository.findById(id)
                .map(entity -> reservationSeatEditMapper.map(reservationDto, entity))
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

    private Set<Long> getReservedSeatIds(Schedule schedule) {
        return schedule.getReservationSeats().stream()
                .map(ReservationSeat::getSeat)
                .map(Seat::getId).collect(Collectors.toSet());
    }
}
