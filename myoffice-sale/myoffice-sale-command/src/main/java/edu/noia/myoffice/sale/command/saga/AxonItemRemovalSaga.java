package edu.noia.myoffice.sale.command.saga;

import edu.noia.myoffice.common.application.command.CommandPublisher;
import edu.noia.myoffice.sale.application.saga.ItemRemovalSaga;
import edu.noia.myoffice.sale.domain.article.SystemCancelledArticleReservation;
import edu.noia.myoffice.sale.domain.cart.ItemRemovedFromCart;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

@Saga
public class AxonItemRemovalSaga extends ItemRemovalSaga {

    @Override
    @StartSaga
    @SagaEventHandler(associationProperty = "cartId")
    public void on(ItemRemovedFromCart event, CommandPublisher commandPublisher) {
        super.on(event, commandPublisher);
    }

    @Override
    @EndSaga
    @SagaEventHandler(associationProperty = "cartId")
    public void on(SystemCancelledArticleReservation event, CommandPublisher commandPublisher) {
        super.on(event, commandPublisher);
    }
}
