package edu.noia.myoffice.sale.domain.cart;

import edu.noia.myoffice.common.domain.vo.Amount;
import edu.noia.myoffice.sale.domain.folder.FolderEvent;
import edu.noia.myoffice.sale.domain.folder.FolderId;
import edu.noia.myoffice.sale.domain.order.OrderId;
import lombok.*;

@NonNull
@Getter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class CartOrdered implements CartEvent, FolderEvent {
    CartId cartId;
    CartType cartType;
    FolderId folderId;
    OrderId orderId;
    Amount amount;
}
