package edu.noia.myoffice.sale.application.command.cart;

import edu.noia.myoffice.common.domain.vo.Amount;
import edu.noia.myoffice.sale.domain.cart.CartId;
import edu.noia.myoffice.sale.domain.folder.FolderId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceCartCommand implements CartCommand {

    @NonNull
    CartId cartId;
    @NonNull
    FolderId folderId;
    @NonNull
    Amount amount;
}
