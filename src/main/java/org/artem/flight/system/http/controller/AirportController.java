
package org.artem.flight.system.http.controller;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.database.repository.CountryRepository;
import org.artem.flight.system.dto.AirportCreateEditDto;
import org.artem.flight.system.service.CityService;
import org.artem.flight.system.service.AirportService;
import org.artem.flight.system.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/airports")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;
    private final CountryService countryRepository;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("airports", airportService.findAll());

        return "airport/airports";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        return airportService.findById(id)
                .map(airport -> {
                    model.addAttribute("airport", airport);
                    return "airport/airport";
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/create")
    public String create(Model model,
                         @ModelAttribute("airport") AirportCreateEditDto airport) {
        model.addAttribute("airport", airport);
        model.addAttribute("countries", countryRepository.findAll());
        return "airport/airportCreate";
    }

    @PostMapping("/createAirport")
    public String createAirport(@Validated AirportCreateEditDto airport,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("airport", airport);
            redirectAttributes.addFlashAttribute("bindingResult", bindingResult);
            return "redirect:/airports/create";
        }

        return "redirect:/airports/" + airportService.createWithCity(airport).getId();
    }

    @GetMapping("{id}/update")
    public String update(@PathVariable Long id, Model model) {
        return airportService.findById(id)
                .map(airport -> {
                    model.addAttribute("airport", airport);
                    model.addAttribute("countries", countryRepository.findAll());
                    return "airport/airportEdit";
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("{id}/updateAirport")
    public String updateAirport(@PathVariable Long id,
                                 @Validated AirportCreateEditDto airport,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("airport", airport);
            redirectAttributes.addFlashAttribute("bindingResult", bindingResult);
            return "redirect:/airports/{id}/update";
        }

        return airportService.update(id, airport)
                .map(it -> "redirect:/airports/{id}/update")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable Long id) {
        if (!airportService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "redirect:/airports";
    }
}
