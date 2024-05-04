package edu.noia.myoffice.sale.application.saga;

import edu.noia.myoffice.common.application.command.CommandPublisher;
import edu.noia.myoffice.common.domain.vo.Amount;
import edu.noia.myoffice.sale.application.command.article.ConfirmArticleListCommand;
import edu.noia.myoffice.sale.application.command.article.ReserveArticleListCommand;
import edu.noia.myoffice.sale.application.command.cart.CloseCartCommand;
import edu.noia.myoffice.sale.application.command.cart.InvoiceCartCommand;
import edu.noia.myoffice.sale.application.command.order.CancelOrderCommand;
import edu.noia.myoffice.sale.domain.article.ArticleListConfirmed;
import edu.noia.myoffice.sale.domain.article.ArticleListReserved;
import edu.noia.myoffice.sale.domain.article.SystemFailedToReserveArticleList;
import edu.noia.myoffice.sale.domain.cart.CartId;
import edu.noia.myoffice.sale.domain.cart.CartOrdered;
import edu.noia.myoffice.sale.domain.cart.CartType;
import edu.noia.myoffice.sale.domain.folder.FolderId;
import edu.noia.myoffice.sale.domain.invoice.InvoiceCreated;
import edu.noia.myoffice.sale.domain.invoice.InvoiceId;
import edu.noia.myoffice.sale.domain.order.OrderCancelled;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartOrderingSaga {

    CartId cartId;
    FolderId folderId;
    InvoiceId invoiceId;
    Amount amount;
    boolean articleListConfirmed = false;

    /**
     * Start of saga
     */
    public void on(CartOrdered event, CommandPublisher commandPublisher) {
        setFolderId(event.getFolderId());
        setCartId(event.getCartId());
        setAmount(event.getAmount());
        if (event.getCartType() == CartType.LOG) {
            commandPublisher.publish(ConfirmArticleListCommand.of(event.getCartId(), new HashMap<>()));
            commandPublisher.publish(InvoiceCartCommand.of(event.getCartId(), event.getFolderId(), event.getAmount()));
        } else {
            commandPublisher.publish(ReserveArticleListCommand.of(event.getCartId(), new HashMap<>()));
        }
    }

    /**
     * Continuation of saga
     */
    public void on(ArticleListReserved event, CommandPublisher commandPublisher) {
        commandPublisher.publish(ConfirmArticleListCommand.of(getCartId(), new HashMap<>()));
        commandPublisher.publish(InvoiceCartCommand.of(getCartId(), getFolderId(), getAmount()));
    }

    /**
     * Continuation or end of saga
     */
    public void on(ArticleListConfirmed event, CommandPublisher commandPublisher) {
        setArticleListConfirmed(true);
        if (getInvoiceId() != null) {
            commandPublisher.publish(CloseCartCommand.of(getCartId(), getInvoiceId()));
        }
    }

    /**
     * Continuation or end of saga
     */
    public void on(InvoiceCreated event, CommandPublisher commandPublisher) {
        setInvoiceId(event.getInvoiceId());
        if (isArticleListConfirmed()) {
            commandPublisher.publish(CloseCartCommand.of(getCartId(), event.getInvoiceId()));
        }
    }

    /**
     * Compensation
     */
    public void on(SystemFailedToReserveArticleList event, CommandPublisher commandPublisher) {
        commandPublisher.publish(CancelOrderCommand.of(getCartId()));
    }

    /**
     * End of saga
     */
    public void on(OrderCancelled event, CommandPublisher commandPublisher) {
    }
}
