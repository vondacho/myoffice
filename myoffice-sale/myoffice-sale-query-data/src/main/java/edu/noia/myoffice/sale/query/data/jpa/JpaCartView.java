package edu.noia.myoffice.sale.query.data.jpa;

import edu.noia.myoffice.common.data.jpa.JpaBaseEntity;
import edu.noia.myoffice.sale.domain.cart.CartId;
import edu.noia.myoffice.sale.domain.cart.CartItem;
import edu.noia.myoffice.sale.domain.cart.CartItemId;
import edu.noia.myoffice.sale.domain.cart.CartType;
import edu.noia.myoffice.sale.domain.folder.FolderId;
import edu.noia.myoffice.sale.domain.invoice.InvoiceId;
import edu.noia.myoffice.sale.domain.order.OrderId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity(name = "cart")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Data
public class JpaCartView extends JpaBaseEntity {

    CartId id;
    FolderId folderId;
    @Enumerated(EnumType.STRING)
    CartType type;
    String title;
    String notes;
    OrderId orderId;
    InvoiceId invoiceId;

    @Type(type = "edu.noia.myoffice.sale.query.data.jpa.hibernate.type.CartItemType")
    @Columns(columns = {
            @Column(name = "id"),
            @Column(name = "articleId"),
            @Column(name = "title"),
            @Column(name = "tariff_price"),
            @Column(name = "tariff_target_quantity"),
            @Column(name = "tariff_target_unit"),
            @Column(name = "quantity"),
            @Column(name = "quantity_unit"),
            @Column(name = "timestamp")
    })
    @ElementCollection
    @CollectionTable(name = "t_cart_items", joinColumns = @JoinColumn(name = "fk_cart"))
    List<CartItem> items = new ArrayList<>();

    public JpaCartView add(CartItem... cartItems) {
        items.addAll(Arrays.asList(cartItems));
        return this;
    }

    public JpaCartView remove(CartItemId itemId) {
        items.removeIf(item -> item.getId().equals(itemId));
        return this;
    }
}
