package edu.noia.myoffice.sale.domain.cart;

import edu.noia.myoffice.sale.domain.folder.FolderId;
import edu.noia.myoffice.sale.domain.invoice.InvoiceId;
import edu.noia.myoffice.sale.domain.order.OrderId;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface CartState {

    @NotNull
    FolderId getFolderId();

    @NotNull
    CartType getType();

    String getTitle();

    String getNotes();

    default List<CartItem> getItems() {
        return Collections.emptyList();
    }

    default Optional<CartItem> getItem(CartItemId itemId) {
        return Optional.empty();
    }

    default Optional<OrderId> getOrderId() {
        return Optional.empty();
    }

    default Optional<InvoiceId> getInvoiceId() {
        return Optional.empty();
    }
}
