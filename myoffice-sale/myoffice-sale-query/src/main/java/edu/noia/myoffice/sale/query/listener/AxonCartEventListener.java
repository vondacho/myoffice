package edu.noia.myoffice.sale.query.listener;

import edu.noia.myoffice.sale.domain.cart.CartCreated;
import edu.noia.myoffice.sale.domain.cart.CartInvoiced;
import edu.noia.myoffice.sale.domain.cart.CartOrdered;
import edu.noia.myoffice.sale.domain.cart.ItemAddedToCart;
import edu.noia.myoffice.sale.domain.cart.ItemRemovedFromCart;
import edu.noia.myoffice.sale.query.handler.CartEventHandler;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.axonframework.eventhandling.EventHandler;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AxonCartEventListener {

    @NonNull
    CartEventHandler handler;

    @EventHandler
    public void on(CartCreated event) {
        handler.on(event);
    }

    @EventHandler
    public void on(ItemAddedToCart event) {
        handler.on(event);
    }

    @EventHandler
    public void on(ItemRemovedFromCart event) {
        handler.on(event);
    }

    @EventHandler
    public void on(CartOrdered event) {
        handler.on(event);
    }

    @EventHandler
    public void on(CartInvoiced event) {
        handler.on(event);
    }
}
