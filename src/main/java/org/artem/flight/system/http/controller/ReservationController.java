package org.artem.flight.system.http.controller;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.dto.ReservationSeatCreateEditDto;
import org.artem.flight.system.service.ReservationSeatService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationSeatService reservationService;


    @PostMapping("/createReservation")
    public String createReservation(@Validated ReservationSeatCreateEditDto reservation,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("schedule", reservation);
            redirectAttributes.addFlashAttribute("bindingResult", bindingResult);
            return "redirect:/schedules/create";
        }

        var reservationDto = reservationService.create(reservation);
        return "redirect:/schedules/" + reservation.getScheduleId() + "/update";
    }
}