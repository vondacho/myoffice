package edu.noia.myoffice.sale.domain.invoice;

import edu.noia.myoffice.sale.domain.cart.CartEvent;
import edu.noia.myoffice.sale.domain.cart.CartId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class InvoiceCreated implements InvoiceEvent, CartEvent {
    CartId cartId;
    InvoiceId invoiceId;
}
