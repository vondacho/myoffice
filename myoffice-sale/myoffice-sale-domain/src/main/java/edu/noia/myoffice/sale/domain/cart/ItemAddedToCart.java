package edu.noia.myoffice.sale.domain.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NonNull
@Getter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class ItemAddedToCart implements CartEvent {
    CartId cartId;
    CartItem cartItem;
}
