
package org.artem.flight.system.http.controller;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.dto.*;
import org.artem.flight.system.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ReservationSeatService reservationSeatService;
    private final AirlineService airlineService;
    private final FlightService flightService;
    private final AirportService airportService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("schedules", scheduleService.findAll());

        return "schedule/schedules";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        return scheduleService.findById(id)
                .map(schedule -> {
                    model.addAttribute("schedule", schedule);
                    return "schedule/schedule";
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/create")
    public String create(Model model,
                         @ModelAttribute("schedule") ScheduleCreateEditDto schedule) {
        model.addAttribute("schedule", schedule);
        model.addAttribute("airlines", airlineService.findAll());
        model.addAttribute("flights", flightService.findAll());
        model.addAttribute("airports", airportService.findAll());

        return "schedule/scheduleCreate";
    }

    @PostMapping("/createSchedule")
    public String createSchedule(@Validated ScheduleCreateEditDto schedule,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("schedule", schedule);
            redirectAttributes.addFlashAttribute("bindingResult", bindingResult);
            return "redirect:/schedules/create";
        }

        var scheduleReadDto = scheduleService.create(schedule);
        var reservations = reservationSeatService.create(new ReservationSeatCreateEditDto(scheduleReadDto.getId()));
        return "redirect:/schedules/" + scheduleReadDto.getId() + "/update";
    }

    @GetMapping("{id}/update")
    public String update(@PathVariable Long id, Model model) {
        return scheduleService.findById(id)
                .map(schedule -> {
                    model.addAttribute("schedule", schedule);
                    model.addAttribute("airlines", airlineService.findAll());
                    model.addAttribute("flights", flightService.findAll());
                    model.addAttribute("airports", airportService.findAll());
                    return "schedule/scheduleEdit";
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("{id}/updateSchedule")
    public String updateSchedule(@PathVariable Long id,
                                @Validated ScheduleCreateEditDto schedule,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("schedule", schedule);
            redirectAttributes.addFlashAttribute("bindingResult", bindingResult);
            return "redirect:/schedules/{id}/update";
        }

        return scheduleService.update(id, schedule)
                .map(it -> "redirect:/schedules/{id}/update")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable Long id) {
        if (!scheduleService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "redirect:/schedules";
    }
}
