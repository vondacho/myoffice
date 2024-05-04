package edu.noia.myoffice.sale.domain.cart;

import edu.noia.myoffice.common.util.holder.Holder;
import edu.noia.myoffice.sale.domain.cart.CartEntity;
import edu.noia.myoffice.sale.domain.cart.CartState;
import edu.noia.myoffice.sale.domain.cart.CartId;

import java.util.Optional;

public interface CartRepository {

    Optional<Holder<CartEntity>> findOne(CartId cartId);

    Holder<CartEntity> save(CartId id, CartState state);
}
