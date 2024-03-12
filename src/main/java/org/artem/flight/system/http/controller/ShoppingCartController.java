package org.artem.flight.system.http.controller;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.dto.ShoppingCartCreateEditDto;
import org.artem.flight.system.service.ScheduleService;
import org.artem.flight.system.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@RequestMapping("/shopping-cart")
@Controller
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final ScheduleService scheduleService;

    @GetMapping
    public String find(HttpSession session, Model model) {
        return shoppingCartService.findBy(session.getId())
                .filter(shoppingCart -> !shoppingCart.getItems().isEmpty())
                .map(shoppingCart -> {
                    model.addAttribute("shoppingCart", shoppingCart);
                    return "shoppingCart/shoppingCart";
                }).orElse("shoppingCart/shoppingCartEmpty");
    }

    @PostMapping("/updateShoppingCart")
    public String updateShoppingCart(String sessionId,
                                     @Validated ShoppingCartCreateEditDto shoppingCart,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("shoppingCart", shoppingCart);
            redirectAttributes.addFlashAttribute("bindingResult", bindingResult);
            return "redirect:/shopping-cart/{id}/update";
        }

        return shoppingCartService.update(sessionId, shoppingCart)
                .map(it -> "redirect:/shopping-cart/{id}/update")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/delete")
    public String delete(String sessionId) {
        if (!shoppingCartService.delete(sessionId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "redirect:/shopping-cart";
    }

    @GetMapping("{id}/put")
    public String put(@PathVariable("id") Long reservationSeatId, HttpSession session) {
        shoppingCartService.putItem(reservationSeatId, session.getId());

        return "redirect:/schedules" + scheduleService.findByReservationId(reservationSeatId)
                .map(schedule -> "/" + schedule.getId())
                .orElseThrow();
    }
}