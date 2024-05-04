package edu.noia.myoffice.sale.domain.cart;

import edu.noia.myoffice.common.domain.event.DomainEvent;

public interface CartEvent extends DomainEvent {

    CartId getCartId();
}
