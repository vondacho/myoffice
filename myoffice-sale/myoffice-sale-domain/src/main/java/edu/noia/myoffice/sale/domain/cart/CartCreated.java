package edu.noia.myoffice.sale.domain.cart;

import edu.noia.myoffice.sale.domain.folder.FolderId;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class CartCreated implements CartEvent {

    CartId cartId;
    FolderId folderId;
    CartType type;
    String title;
    String notes;

    public static CartCreated from(CartId id, CartState state) {
        return CartCreated.of(id, state.getFolderId(), state.getType(), state.getTitle(), state.getNotes());
    }
}
