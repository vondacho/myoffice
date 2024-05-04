package edu.noia.myoffice.sale.rest.endpoint;

import edu.noia.myoffice.common.rest.util.IdentifiantPropertyEditorSupport;
import edu.noia.myoffice.sale.domain.cart.CartId;
import edu.noia.myoffice.sale.rest.event.SaleEvent;
import edu.noia.myoffice.sale.rest.broker.SaleEventBroker;
import edu.noia.myoffice.sale.rest.broker.SaleEventBrokerFluxSinkRegistrar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.UUID;

import static edu.noia.myoffice.sale.rest.processor.Processors.onlyCartEvent;

@Slf4j
@RestController
@RequestMapping("/api/sale/v1/carts/{id}/events")
public class CartEventEndpoint {

    @Autowired
    SaleEventBroker saleEventBroker;

    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<SaleEvent> cartEvents(@PathVariable("id") CartId cartId) {
        return Flux.create(sink -> SaleEventBrokerFluxSinkRegistrar.register(sink,
                event -> onlyCartEvent(cartId).apply(event), saleEventBroker));
    }

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.registerCustomEditor(CartId.class,
                new IdentifiantPropertyEditorSupport<>(s -> CartId.of(UUID.fromString(s))));
    }
}
