package edu.noia.myoffice.sale.domain.cart;

import edu.noia.myoffice.sale.domain.cart.CartEvent;
import edu.noia.myoffice.sale.domain.cart.CartId;
import edu.noia.myoffice.sale.domain.cart.CartItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class ItemCreated implements CartEvent {
    CartId cartId;
    CartItem cartItem;
}
