package edu.noia.myoffice.sale.domain.cart;

import edu.noia.myoffice.sale.domain.folder.FolderId;
import edu.noia.myoffice.sale.domain.invoice.InvoiceId;
import edu.noia.myoffice.sale.domain.order.OrderId;
import lombok.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Builder(builderMethodName = "hiddenBuilder")
public class CartSample implements CartState {

    @Getter
    FolderId folderId;
    @Getter
    CartType type;
    @Getter
    String title;
    @Getter
    String notes;
    @Singular
    List<CartItem> items;
    OrderId orderId;
    InvoiceId invoiceId;

    public static CartSampleBuilder builder(@NonNull FolderId folderId, @NonNull CartType cartType) {
        return hiddenBuilder().folderId(folderId).type(cartType);
    }

    public static CartSampleBuilder builder(CartState state) {
        return hiddenBuilder()
                .folderId(state.getFolderId())
                .type(state.getType())
                .title(state.getTitle())
                .notes(state.getNotes())
                .items(state.getItems());
    }

    @Override
    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    @Override
    public Optional<CartItem> getItem(CartItemId itemId) {
        return items.stream().filter(item -> item.getId().equals(itemId)).findFirst();
    }

    @Override
    public Optional<OrderId> getOrderId() {
        return Optional.ofNullable(orderId);
    }

    @Override
    public Optional<InvoiceId> getInvoiceId() {
        return Optional.ofNullable(invoiceId);
    }
}
