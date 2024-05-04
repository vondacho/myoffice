package edu.noia.myoffice.sale.domain.cart;

import lombok.*;

@NonNull
@Getter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class ItemRemovedFromCart implements CartEvent, ItemEvent {
    CartId cartId;
    CartItemId cartItemId;
}
