package edu.noia.myoffice.sale.query.handler;

import edu.noia.myoffice.sale.domain.cart.CartCreated;
import edu.noia.myoffice.sale.domain.cart.CartInvoiced;
import edu.noia.myoffice.sale.domain.cart.CartOrdered;
import edu.noia.myoffice.sale.domain.cart.ItemAddedToCart;
import edu.noia.myoffice.sale.domain.cart.ItemRemovedFromCart;

public interface CartEventHandler {

    void on(CartCreated event);

    void on(ItemAddedToCart event);

    void on(ItemRemovedFromCart event);

    void on(CartOrdered event);

    void on(CartInvoiced event);
}
