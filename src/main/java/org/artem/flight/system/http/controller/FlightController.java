
package org.artem.flight.system.http.controller;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.database.entity.SeatRank;
import org.artem.flight.system.dto.FlightCreateEditDto;
import org.artem.flight.system.dto.SeatCreateEditDto;
import org.artem.flight.system.service.AirlineService;
import org.artem.flight.system.service.FlightService;
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
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;
    private final SeatService seatService;
    private final AirlineService airlineService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("flights", flightService.findAll());

        return "flight/flights";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        return flightService.findById(id)
                .map(flight -> {
                    model.addAttribute("flight", flight);
                    model.addAttribute("seatRanks", SeatRank.values());
                    return "flight/flight";
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/create")
    public String create(Model model,
                         @ModelAttribute("flight") FlightCreateEditDto flight) {
        model.addAttribute("flight", flight);
        model.addAttribute("seatRanks", SeatRank.values());
        model.addAttribute("airlines", airlineService.findAll());

        return "flight/flightCreate";
    }

    @PostMapping("/createFlight")
    public String createFlight(@Validated FlightCreateEditDto flight,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("flight", flight);
            redirectAttributes.addFlashAttribute("bindingResult", bindingResult);
            return "redirect:/flights/create";
        }

        return "redirect:/flights/" + flightService.create(flight).getId();
    }

    @PostMapping("/createSeat")
    public String createSeat(@Validated SeatCreateEditDto seat,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        var flightId = seat.getFlightId();
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("seat", seat);
            redirectAttributes.addFlashAttribute("bindingResult", bindingResult);
            return "redirect:/flights/" + flightId;
        }

        seatService.create(seat);
        return "redirect:/flights/" + flightId;
    }

    @GetMapping("{id}/update")
    public String update(@PathVariable Long id, Model model) {
        return flightService.findById(id)
                .map(flight -> {
                    model.addAttribute("flight", flight);
                    model.addAttribute("airlines", airlineService.findAll());
                    return "flight/flightEdit";
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("{id}/updateFlight")
    public String updateFlight(@PathVariable Long id,
                               @Validated FlightCreateEditDto flight,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("flight", flight);
            redirectAttributes.addFlashAttribute("bindingResult", bindingResult);
            return "redirect:/flights/{id}/update";
        }

        return flightService.update(id, flight)
                .map(it -> "redirect:/flights/{id}/update")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable Long id) {
        if (!flightService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "redirect:/flights";
    }
}
