package edu.noia.myoffice.sale.query.data.adapter;

import edu.noia.myoffice.sale.domain.cart.CartCreated;
import edu.noia.myoffice.sale.domain.cart.CartInvoiced;
import edu.noia.myoffice.sale.domain.cart.CartOrdered;
import edu.noia.myoffice.sale.domain.cart.ItemAddedToCart;
import edu.noia.myoffice.sale.domain.cart.ItemRemovedFromCart;
import edu.noia.myoffice.sale.query.data.jpa.JpaCartViewFactory;
import edu.noia.myoffice.sale.query.data.jpa.JpaCartViewRepository;
import edu.noia.myoffice.sale.query.handler.CartEventHandler;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartEventHandlerAdapter implements CartEventHandler {

    @NonNull
    JpaCartViewRepository repository;

    public void on(CartCreated event) {
        repository.save(JpaCartViewFactory.from(event));
    }

    public void on(ItemAddedToCart event) {
        repository
                .findById(event.getCartId())
                .ifPresent(cart -> repository.save(cart.add(event.getCartItem())));
    }

    public void on(ItemRemovedFromCart event) {
        repository
                .findById(event.getCartId())
                .ifPresent(cart -> repository.save(cart.remove(event.getCartItemId())));
    }

    public void on(CartOrdered event) {
        repository
                .findById(event.getCartId())
                .ifPresent(cart -> repository.save(cart.setOrderId(event.getOrderId())));
    }

    public void on(CartInvoiced event) {
        repository
                .findById(event.getCartId())
                .ifPresent(cart -> repository.save(cart.setInvoiceId(event.getInvoiceId())));
    }
}
