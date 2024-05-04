package edu.noia.myoffice.sale.command.saga;

import edu.noia.myoffice.common.application.command.CommandPublisher;
import edu.noia.myoffice.sale.application.saga.CartOrderingSaga;
import edu.noia.myoffice.sale.domain.article.ArticleListConfirmed;
import edu.noia.myoffice.sale.domain.article.ArticleListReserved;
import edu.noia.myoffice.sale.domain.article.SystemFailedToReserveArticleList;
import edu.noia.myoffice.sale.domain.cart.CartOrdered;
import edu.noia.myoffice.sale.domain.invoice.InvoiceCreated;
import edu.noia.myoffice.sale.domain.order.OrderCancelled;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import static org.axonframework.modelling.saga.SagaLifecycle.end;

@Saga
public class AxonCartOrderingSaga extends CartOrderingSaga {

    @Override
    @StartSaga
    @SagaEventHandler(associationProperty = "cartId")
    public void on(CartOrdered event, CommandPublisher commandPublisher) {
        super.on(event, commandPublisher);
    }

    @Override
    @SagaEventHandler(associationProperty = "cartId")
    public void on(ArticleListReserved event, CommandPublisher commandPublisher) {
        super.on(event, commandPublisher);
    }

    @Override
    @SagaEventHandler(associationProperty = "cartId")
    public void on(ArticleListConfirmed event, CommandPublisher commandPublisher) {
        super.on(event, commandPublisher);
        if (getInvoiceId() != null) {
            end();
        }
    }

    @Override
    @SagaEventHandler(associationProperty = "cartId")
    public void on(InvoiceCreated event, CommandPublisher commandPublisher) {
        super.on(event, commandPublisher);
        if (isArticleListConfirmed()) {
            end();
        }
    }

    @Override
    @SagaEventHandler(associationProperty = "cartId")
    public void on(SystemFailedToReserveArticleList event, CommandPublisher commandPublisher) {
        super.on(event, commandPublisher);
    }

    @Override
    @EndSaga
    @SagaEventHandler(associationProperty = "cartId")
    public void on(OrderCancelled event, CommandPublisher commandPublisher) {
        super.on(event, commandPublisher);
    }
}
