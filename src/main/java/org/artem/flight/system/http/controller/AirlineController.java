
package org.artem.flight.system.http.controller;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.dto.AirlineCreateEditDto;
import org.artem.flight.system.service.AirlineService;
import org.artem.flight.system.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/airlines")
@RequiredArgsConstructor
public class AirlineController {

    private final AirlineService airlineService;
    private final CountryService countryRepository;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("airlines", airlineService.findAll());

        return "airline/airlines";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        return airlineService.findById(id)
                .map(airline -> {
                    model.addAttribute("airline", airline);
                    return "airline/airline";
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/create")
    public String create(Model model,
                         @ModelAttribute("airline") AirlineCreateEditDto airline) {
        model.addAttribute("airline", airline);
        model.addAttribute("countries", countryRepository.findAll());
        return "airline/airlineCreate";
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/createAirline")
    public String createAirline(@Validated AirlineCreateEditDto airline,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("airline", airline);
            redirectAttributes.addFlashAttribute("bindingResult", bindingResult);
            return "redirect:/airlines/create";
        }

        return "redirect:/airlines/" + airlineService.create(airline).getId();
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("{id}/update")
    public String update(@PathVariable Long id, Model model) {
        return airlineService.findById(id)
                .map(airline -> {
                    model.addAttribute("airline", airline);
                    model.addAttribute("countries", countryRepository.findAll());
                    return "airline/airlineEdit";
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("{id}/updateAirline")
    public String updateAirline(@PathVariable Long id,
                                 @Validated AirlineCreateEditDto airline,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("airline", airline);
            redirectAttributes.addFlashAttribute("bindingResult", bindingResult);
            return "redirect:/airlines/{id}/update";
        }

        return airlineService.update(id, airline)
                .map(it -> "redirect:/airlines/{id}/update")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("{id}/delete")
    public String delete(@PathVariable Long id) {
        if (!airlineService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "redirect:/airlines";
    }
}
