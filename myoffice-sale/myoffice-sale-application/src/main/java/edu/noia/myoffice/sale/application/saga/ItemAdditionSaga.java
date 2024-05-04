package edu.noia.myoffice.sale.application.saga;

import edu.noia.myoffice.common.application.command.CommandPublisher;
import edu.noia.myoffice.sale.application.command.article.ReserveArticleCommand;
import edu.noia.myoffice.sale.application.command.item.DeposeItemIntoCartCommand;
import edu.noia.myoffice.sale.domain.article.SystemFailedToReserveArticle;
import edu.noia.myoffice.sale.domain.article.SystemReservedArticle;
import edu.noia.myoffice.sale.domain.cart.CartId;
import edu.noia.myoffice.sale.domain.cart.CartItem;
import edu.noia.myoffice.sale.domain.cart.ItemAddedToCart;
import edu.noia.myoffice.sale.domain.cart.ItemCreated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemAdditionSaga {

    CartId cartId;
    CartItem cartItem;

    /**
     * Start of saga
     */
    public void on(ItemCreated event, CommandPublisher commandPublisher) {
        setCartId(event.getCartId());
        setCartItem(event.getCartItem());
        commandPublisher.publish(
                ReserveArticleCommand.of(
                        event.getCartId(),
                        event.getCartItem().getArticle().getArticleId(),
                        event.getCartItem().getQuantity()));
    }

    /**
     * Continuation of Saga
     */
    public void on(SystemReservedArticle event, CommandPublisher commandPublisher) {
        commandPublisher.publish(
                DeposeItemIntoCartCommand.of(
                        event.getCartId(),
                        getCartItem()));
    }

    /**
     * End of saga
     */
    public void on(ItemAddedToCart event, CommandPublisher commandPublisher) {
    }

    /**
     * End of saga
     */
    public void on(SystemFailedToReserveArticle event, CommandPublisher commandPublisher) {
    }
}
