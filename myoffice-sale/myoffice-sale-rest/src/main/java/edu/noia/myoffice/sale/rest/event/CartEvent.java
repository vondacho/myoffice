package edu.noia.myoffice.sale.rest.event;

import edu.noia.myoffice.sale.domain.cart.CartId;
import org.springframework.hateoas.EntityModel;

import java.time.Instant;

public class CartEvent extends SaleEvent<EntityModel<CartId>> {

    public CartEvent(Instant timestamp, Class eventClass, EntityModel<CartId> payload) {
        super(timestamp, eventClass, payload);
    }
}
