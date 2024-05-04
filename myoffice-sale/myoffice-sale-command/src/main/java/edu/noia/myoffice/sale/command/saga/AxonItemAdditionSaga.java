package edu.noia.myoffice.sale.command.saga;

import edu.noia.myoffice.common.application.command.CommandPublisher;
import edu.noia.myoffice.sale.application.saga.ItemAdditionSaga;
import edu.noia.myoffice.sale.domain.article.SystemFailedToReserveArticle;
import edu.noia.myoffice.sale.domain.article.SystemReservedArticle;
import edu.noia.myoffice.sale.domain.cart.ItemAddedToCart;
import edu.noia.myoffice.sale.domain.cart.ItemCreated;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

@Saga
public class AxonItemAdditionSaga extends ItemAdditionSaga {

    @Override
    @StartSaga
    @SagaEventHandler(associationProperty = "cartId")
    public void on(ItemCreated event, CommandPublisher commandPublisher) {
        super.on(event, commandPublisher);
    }

    @Override
    @EndSaga
    @SagaEventHandler(associationProperty = "cartId")
    public void on(SystemFailedToReserveArticle event, CommandPublisher commandPublisher) {
        super.on(event, commandPublisher);
    }

    @Override
    @SagaEventHandler(associationProperty = "cartId")
    public void on(SystemReservedArticle event, CommandPublisher commandPublisher) {
        super.on(event, commandPublisher);
    }

    @Override
    @EndSaga
    @SagaEventHandler(associationProperty = "cartId")
    public void on(ItemAddedToCart event, CommandPublisher commandPublisher) {
        super.on(event, commandPublisher);
    }

}
