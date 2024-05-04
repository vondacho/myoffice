package edu.noia.myoffice.sale.domain.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class ItemAvailabilityDismissed implements CartEvent, ItemEvent {
    CartId cartId;
    CartItemId cartItemId;
    Long availableQuantity;
}
