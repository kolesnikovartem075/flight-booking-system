package org.artem.flight.system.http.controller;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.dto.CustomerCreateEditDto;
import org.artem.flight.system.service.CustomerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/customers")
@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/registration")
    @PreAuthorize("isAnonymous()")
    public String create(Model model,
                         @ModelAttribute("customer") CustomerRegistrationCreateEditDto customer) {
        model.addAttribute("customer", customer);

        return "registration";
    }

    @PostMapping("/create")
    @PreAuthorize("isAnonymous()")
    public String create(@Validated CustomerRegistrationCreateEditDto customer,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("customer", customer);
            redirectAttributes.addFlashAttribute("bindingResult", bindingResult);
            return "redirect:/registration";
        }
        var id = customerService.create(customer).getId();
        return "redirect:/schedules/";
    }
}
