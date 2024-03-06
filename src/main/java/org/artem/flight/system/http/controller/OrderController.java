package org.artem.flight.system.http.controller;


import lombok.RequiredArgsConstructor;
import org.artem.flight.system.database.entity.OrderStatus;
import org.artem.flight.system.dto.OrderCreateEditDto;
import org.artem.flight.system.dto.ShoppingCartReadDto;
import org.artem.flight.system.service.OrderService;
import org.artem.flight.system.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@RequestMapping("/orders")
@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;

    @GetMapping
    public String findAll(Model model) {
        var orders = orderService.findAll();
        model.addAttribute("orders", orders);

        return "order/orders";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        return orderService.findById(id)
                .map(order -> {
                    model.addAttribute("order", order);
                    model.addAttribute("statusList", OrderStatus.values());
                    return "order/order";
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/create")
    public String create(@ModelAttribute OrderCreateEditDto order, Model model, HttpSession session) {
        var shoppingCartReadDto = getShoppingCartReadDto(session.getId());

        model.addAttribute("shoppingCart", shoppingCartReadDto);
        model.addAttribute("order", order);

        return "order/orderCreate";
    }

    @PostMapping("/createOrder")
    public String createOrder(@Validated OrderCreateEditDto order,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              HttpSession session) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("order", order);
            redirectAttributes.addFlashAttribute("bindingResult", bindingResult);
            return "redirect:/shopping-cart/create";
        }
        order.setSessionId(session.getId());
        return "redirect:/orders/" + orderService.createOrder(order).getId();
    }


    @GetMapping("{id}/update")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String update(@PathVariable Long id, Model model) {
        return orderService.findById(id)
                .map(order -> {
                    model.addAttribute("order", order);
                    model.addAttribute("statusList", OrderStatus.values());
                    return "order/orderEdit";
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @PostMapping("{id}/updateOrder")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String updateOrder(@PathVariable Long id,
                              @Validated OrderCreateEditDto order,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("order", order);
            redirectAttributes.addFlashAttribute("statusList", OrderStatus.values());
            redirectAttributes.addFlashAttribute("bindingResult", bindingResult);
            return "redirect:/orders/{id}/update";
        }

        return orderService.update(id, order)
                .map(it -> "redirect:/orders/{id}/update")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("{id}/delete")
    public String delete(@PathVariable Long id) {
        if (!orderService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "redirect:/orders";
    }

    private ShoppingCartReadDto getShoppingCartReadDto(String sessionId) {
        return shoppingCartService.findBy(sessionId)
                .orElseThrow();
    }
}