package edu.noia.myoffice.sale.event.config;

import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.sale.event.adapter.AxonEventPublisherAdapter;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaleEventConfiguration {

    @Bean
    public EventPublisher eventPublisher(EventGateway eventGateway) {
        return new AxonEventPublisherAdapter(eventGateway);
    }
}
