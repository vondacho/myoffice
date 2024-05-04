package edu.noia.myoffice.sale.command.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.noia.myoffice.common.application.command.CommandPublisher;
import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.common.domain.vo.Quantity;
import edu.noia.myoffice.common.domain.vo.Rate;
import edu.noia.myoffice.common.mixin.QuantityMixin;
import edu.noia.myoffice.common.mixin.RateMixin;
import edu.noia.myoffice.common.serializer.CommonSerializers;
import edu.noia.myoffice.sale.application.service.CartUseCase;
import edu.noia.myoffice.sale.application.service.InventoryUseCase;
import edu.noia.myoffice.sale.command.adapter.CommandPublisherAdapter;
import edu.noia.myoffice.sale.command.adapter.SaleCommandCallback;
import edu.noia.myoffice.sale.command.entity.AxonESCartEntity;
import edu.noia.myoffice.sale.command.service.AxonCartUseCase;
import edu.noia.myoffice.sale.command.service.AxonInventoryUseCase;
import edu.noia.myoffice.sale.common.mixin.CartItemMixin;
import edu.noia.myoffice.sale.common.serializer.SaleSerializers;
import edu.noia.myoffice.sale.domain.cart.CartRepository;
import edu.noia.myoffice.sale.domain.cart.CartItem;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.messaging.interceptors.CorrelationDataInterceptor;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@ComponentScan(basePackageClasses = {
        AxonESCartEntity.class,
        AxonCartUseCase.class
})
@Configuration
public class SaleCommandConfiguration {

    @Bean
    public CartUseCase cartUseCase(CartRepository cartRepository, EventPublisher eventPublisher) {
        return new AxonCartUseCase(cartRepository, eventPublisher);
    }

    @Bean
    public InventoryUseCase inventoryUseCase(EventPublisher eventPublisher) {
        return new AxonInventoryUseCase(eventPublisher);
    }

    @Bean
    public CommandPublisher commandPublisher(CommandGateway commandGateway, SaleCommandCallback commandCallback) {
        return new CommandPublisherAdapter(commandGateway, commandCallback);
    }

    @Bean
    public SaleCommandCallback exceptionHandlerCallback() {
        return new SaleCommandCallback();
    }

    @Primary
    @Bean
    public Serializer serializer() {
        return JacksonSerializer.builder().objectMapper(new ObjectMapper()
                .registerModule(CommonSerializers.getModule())
                .registerModule(SaleSerializers.getModule())
                .addMixIn(CartItem.class, CartItemMixin.class)
                .addMixIn(Quantity.class, QuantityMixin.class)
                .addMixIn(Rate.class, RateMixin.class)
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)).build();
    }

    @Bean
    public SimpleCommandBus commandBus(TransactionManager txManager, AxonConfiguration axonConfiguration) {
        SimpleCommandBus commandBus =
                SimpleCommandBus.builder()
                        .transactionManager(txManager)
                        .messageMonitor(axonConfiguration.messageMonitor(CommandBus.class, "commandBus"))
                        .build();
        commandBus.registerHandlerInterceptor(
                new CorrelationDataInterceptor<>(axonConfiguration.correlationDataProviders())
        );
        return commandBus;
    }
}
