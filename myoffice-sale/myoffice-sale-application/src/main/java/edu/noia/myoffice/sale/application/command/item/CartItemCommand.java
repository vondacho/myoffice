package edu.noia.myoffice.sale.application.command.item;

import edu.noia.myoffice.sale.domain.cart.CartItem;

public interface CartItemCommand {
    CartItem getCartItem();
}
