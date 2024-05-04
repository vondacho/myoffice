package edu.noia.myoffice.sale.domain.cart;

import edu.noia.myoffice.sale.domain.invoice.InvoiceEvent;
import edu.noia.myoffice.sale.domain.invoice.InvoiceId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NonNull
@Getter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class CartInvoiced implements CartEvent, InvoiceEvent {
    CartId cartId;
    InvoiceId invoiceId;
}
