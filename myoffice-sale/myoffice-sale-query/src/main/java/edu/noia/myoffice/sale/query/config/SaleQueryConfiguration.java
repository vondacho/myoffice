package edu.noia.myoffice.sale.query.config;

import edu.noia.myoffice.sale.query.listener.AxonCartEventListener;
import edu.noia.myoffice.sale.query.handler.CartEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaleQueryConfiguration {

    @Bean
    public AxonCartEventListener cartEventListener(CartEventHandler cartEventHandler) {
        return new AxonCartEventListener(cartEventHandler);
    }
}
