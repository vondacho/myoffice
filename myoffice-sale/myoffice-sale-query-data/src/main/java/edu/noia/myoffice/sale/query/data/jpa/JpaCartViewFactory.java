package edu.noia.myoffice.sale.query.data.jpa;

import edu.noia.myoffice.sale.domain.cart.CartCreated;

public class JpaCartViewFactory {

    public static JpaCartView from(CartCreated event) {
        return new JpaCartView()
                .setId(event.getCartId())
                .setFolderId(event.getFolderId())
                .setType(event.getType())
                .setTitle(event.getTitle())
                .setNotes(event.getNotes());
    }
}
