package edu.noia.myoffice.sale.event.adapter;

import edu.noia.myoffice.common.domain.event.DomainEvent;
import edu.noia.myoffice.common.domain.event.EventPublisher;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.gateway.EventGateway;

import static org.axonframework.eventhandling.GenericEventMessage.asEventMessage;

@RequiredArgsConstructor
public class AxonEventPublisherAdapter implements EventPublisher {

    @NonNull
    EventGateway eventGateway;

    @Override
    public void publish(DomainEvent event) {
        eventGateway.publish(asEventMessage(event));
    }
}
