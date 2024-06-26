package org.artem.flight.system.http.controller;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.database.entity.ReservationStatus;
import org.artem.flight.system.database.repository.ReservationSeatRepository;
import org.artem.flight.system.dto.ShoppingCartItemCreateEditDto;
import org.artem.flight.system.dto.ShoppingCartItemReadDto;
import org.artem.flight.system.service.ShoppingCartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/shopping-cart-item")
@Controller
@RequiredArgsConstructor
public class ShoppingCartItemController {

    private final ShoppingCartItemService shoppingCartItemService;
    private final ReservationSeatRepository reservationSeatRepository;

    @PostMapping("{id}/updateShoppingCartItem")
    public String updateShoppingCart(@PathVariable Long id,
                                     @Validated ShoppingCartItemCreateEditDto item,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("shoppingCart", item);
            redirectAttributes.addFlashAttribute("bindingResult", bindingResult);

            return "redirect:/shopping-cart";
        }

        return shoppingCartItemService.update(id, item)
                .map(it -> "redirect:/shopping-cart")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable Long id) {
        var reservationSeatReadDto = shoppingCartItemService.findById(id).map(ShoppingCartItemReadDto::getReservationSeat)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        reservationSeatRepository.findById(reservationSeatReadDto.getId())
                .map(it -> {it.setStatus(ReservationStatus.FREE);
                    return it;
                })
                .map(reservationSeatRepository::saveAndFlush);


        if (!shoppingCartItemService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "redirect:/shopping-cart";
    }
}