package edu.noia.myoffice.sale.domain.cart;

import lombok.NonNull;

import java.util.List;

public class CartProblemOccured {
    CartId cartId;

    public CartProblemOccured(@NonNull List<Object> problems, @NonNull CartId cartId) {
        this.cartId = cartId;
    }
}
