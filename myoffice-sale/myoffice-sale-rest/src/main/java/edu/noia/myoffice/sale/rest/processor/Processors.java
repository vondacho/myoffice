package edu.noia.myoffice.sale.rest.processor;

import edu.noia.myoffice.common.domain.event.ProblemEvent;
import edu.noia.myoffice.common.util.processor.Filter;
import edu.noia.myoffice.common.util.processor.Processor;
import edu.noia.myoffice.sale.domain.cart.CartEvent;
import edu.noia.myoffice.sale.domain.cart.CartId;
import edu.noia.myoffice.sale.rest.event.SaleEvent;

import java.time.Instant;
import java.util.Optional;

public class Processors {

    private Processors() {
    }

    public static Processor<ProblemEvent, SaleEvent> toProblemEvent(Instant timestamp) {
        return event -> Optional.of(new SaleEvent(timestamp, event.getClass(), event));
    }

    public static Processor<CartEvent, SaleEvent> toCartEvent(Instant timestamp) {
        return event -> Optional.of(new SaleEvent(timestamp, event.getClass(), event.getCartId()));
    }

    public static Filter<SaleEvent> onlyCartEvent(CartId cartId) {
        return event -> Optional.of(event)
                .filter(e -> e.getEventClass().isAssignableFrom(CartEvent.class))
                .filter(e -> e.getPayload().equals(cartId.getId()));
    }
}
