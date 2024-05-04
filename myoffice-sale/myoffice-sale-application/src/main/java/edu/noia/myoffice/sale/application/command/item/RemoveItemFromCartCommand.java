package edu.noia.myoffice.sale.application.command.item;

import edu.noia.myoffice.sale.application.command.cart.CartCommand;
import edu.noia.myoffice.sale.domain.cart.CartId;
import edu.noia.myoffice.sale.domain.cart.CartItemId;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@RequiredArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RemoveItemFromCartCommand implements CartCommand {
    @NonNull
    CartId cartId;
    @NonNull
    CartItemId cartItemId;
}
