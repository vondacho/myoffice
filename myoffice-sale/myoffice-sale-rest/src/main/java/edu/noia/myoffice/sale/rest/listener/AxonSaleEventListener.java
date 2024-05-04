package edu.noia.myoffice.sale.rest.listener;

import edu.noia.myoffice.common.domain.event.ProblemEvent;
import edu.noia.myoffice.sale.domain.cart.CartEvent;
import edu.noia.myoffice.sale.rest.broker.SaleEventBroker;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;

import java.time.Instant;

@RequiredArgsConstructor
public class AxonSaleEventListener {

    @NonNull
    private SaleEventBroker broker;

    @EventHandler
    public void on(CartEvent event, @Timestamp Instant timestamp) {
        broker.on(event, timestamp);
    }

    @EventHandler
    public void on(ProblemEvent event, @Timestamp Instant timestamp) {
        broker.on(event, timestamp);
    }
}
