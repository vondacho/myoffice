package edu.noia.myoffice.sale.rest.broker;

import edu.noia.myoffice.common.domain.event.ProblemEvent;
import edu.noia.myoffice.common.util.broker.DefaultBroker;
import edu.noia.myoffice.sale.domain.cart.CartEvent;
import edu.noia.myoffice.sale.rest.event.SaleEvent;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.UUID;

import static edu.noia.myoffice.sale.rest.processor.Processors.toCartEvent;
import static edu.noia.myoffice.sale.rest.processor.Processors.toProblemEvent;

@RequiredArgsConstructor
public class SaleEventBroker extends DefaultBroker<SaleEvent, UUID> {

    public void on(CartEvent event, Instant timestamp) {
        toCartEvent(timestamp).apply(event).ifPresent(this);
    }

    public void on(ProblemEvent event, Instant timestamp) {
        toProblemEvent(timestamp).apply(event).ifPresent(this);
    }
}
