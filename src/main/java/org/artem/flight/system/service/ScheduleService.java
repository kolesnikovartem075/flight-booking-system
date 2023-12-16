package org.artem.flight.system.service;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.database.repository.ScheduleRepository;
import org.artem.flight.system.dto.ScheduleCreateEditDto;
import org.artem.flight.system.dto.ScheduleReadDto;
import org.artem.flight.system.mapper.schedule.ScheduleCreateEditMapper;
import org.artem.flight.system.mapper.schedule.ScheduleReadMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {


    private final ScheduleRepository scheduleRepository;
    private final ScheduleReadMapper scheduleReadMapper;
    private final ScheduleCreateEditMapper scheduleCreateEditMapper;

    public List<ScheduleReadDto> findAll() {
        return scheduleRepository.findAll().stream()
                .map(scheduleReadMapper::map)
                .toList();
    }

    public Optional<ScheduleReadDto> findById(Long id) {
        return scheduleRepository.findById(id)
                .map(scheduleReadMapper::map);
    }

    @Transactional
    public ScheduleReadDto create(ScheduleCreateEditDto scheduleDto) {
        return Optional.of(scheduleDto)
                .map(scheduleCreateEditMapper::map)
                .map(scheduleRepository::save)
                .map(scheduleReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<ScheduleReadDto> update(Long id, ScheduleCreateEditDto scheduleDto) {
        return scheduleRepository.findById(id)
                .map(entity -> scheduleCreateEditMapper.map(scheduleDto, entity))
                .map(scheduleRepository::saveAndFlush)
                .map(scheduleReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return scheduleRepository.findById(id)
                .map(entity -> {
                    scheduleRepository.delete(entity);
                    scheduleRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}