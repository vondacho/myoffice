package edu.noia.myoffice.sale.application.saga;

import edu.noia.myoffice.common.application.command.CommandPublisher;
import edu.noia.myoffice.sale.domain.article.SystemCancelledArticleReservation;
import edu.noia.myoffice.sale.domain.cart.CartId;
import edu.noia.myoffice.sale.domain.cart.CartItemId;
import edu.noia.myoffice.sale.domain.cart.ItemRemovedFromCart;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemRemovalSaga {

    CartId cartId;
    CartItemId cartItemId;

    /**
     * Start of saga
     */
    public void on(ItemRemovedFromCart event, CommandPublisher commandPublisher) {
        throw new UnsupportedOperationException();
    }

    /**
     * End of saga
     */
    public void on(SystemCancelledArticleReservation event, CommandPublisher commandPublisher) {
        throw new UnsupportedOperationException();
    }
}
