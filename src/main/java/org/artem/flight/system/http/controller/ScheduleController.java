
package org.artem.flight.system.http.controller;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.dto.FlightCreateEditDto;
import org.artem.flight.system.dto.ScheduleCreateEditDto;
import org.artem.flight.system.dto.SeatCreateEditDto;
import org.artem.flight.system.service.AirlineService;
import org.artem.flight.system.service.FlightService;
import org.artem.flight.system.service.ScheduleService;
import org.artem.flight.system.service.SeatService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final SeatService seatService;
    private final AirlineService airlineService;

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

        return "redirect:/schedules/" + scheduleService.create(schedule).getId();
    }

    @PostMapping("/createSeat")
    public String createSeat(@Validated SeatCreateEditDto seat,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        var scheduleId = seat.getFlightId();
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("seat", seat);
            redirectAttributes.addFlashAttribute("bindingResult", bindingResult);
            return "redirect:/schedules/" + scheduleId;
        }

        seatService.create(seat);
        return "redirect:/schedules/" + scheduleId;
    }

    @GetMapping("{id}/update")
    public String update(@PathVariable Long id, Model model) {
        return scheduleService.findById(id)
                .map(schedule -> {
                    model.addAttribute("schedule", schedule);
                    model.addAttribute("airlines", airlineService.findAll());
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
