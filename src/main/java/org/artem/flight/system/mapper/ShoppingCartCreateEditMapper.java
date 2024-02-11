package org.artem.flight.system.mapper;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.database.entity.ShoppingCart;
import org.artem.flight.system.database.repository.CustomerRepository;
import org.artem.flight.system.dto.ShoppingCartCreateEditDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShoppingCartCreateEditMapper implements Mapper<ShoppingCartCreateEditDto, ShoppingCart> {

    private final CustomerRepository customerRepository;

    @Override
    public ShoppingCart map(ShoppingCartCreateEditDto object) {
        ShoppingCart shoppingCart = new ShoppingCart();
        copy(object, shoppingCart);


        return shoppingCart;
    }

    @Override
    public ShoppingCart map(ShoppingCartCreateEditDto fromObject, ShoppingCart toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(ShoppingCartCreateEditDto object, ShoppingCart shoppingCart) {

        shoppingCart.setSessionId(object.getSessionId());
    }
}
