package edu.noia.myoffice.sale.command.data.config;

import edu.noia.myoffice.sale.command.data.repository.AxonCartRepository;
import edu.noia.myoffice.sale.command.entity.AxonESCartEntity;
import edu.noia.myoffice.sale.domain.cart.CartRepository;
import org.axonframework.common.jpa.SimpleEntityManagerProvider;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventhandling.tokenstore.inmemory.InMemoryTokenStore;
import org.axonframework.eventhandling.tokenstore.jpa.TokenEntry;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.jpa.DomainEventEntry;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.modelling.saga.repository.SagaStore;
import org.axonframework.modelling.saga.repository.jpa.JpaSagaStore;
import org.axonframework.modelling.saga.repository.jpa.SagaEntry;
import org.axonframework.serialization.Serializer;
import org.axonframework.spring.config.AxonConfiguration;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.SQLException;

@EntityScan(basePackageClasses = {
        DomainEventEntry.class,
        SagaEntry.class,
        TokenEntry.class
})
@Configuration
public class SaleCommandDataConfiguration {

    @Bean
    public EventStore eventStore(Serializer serializer,
                                 DataSource dataSource,
                                 EntityManager entityManager,
                                 PlatformTransactionManager transactionManager) throws SQLException {
        return new EmbeddedEventStore.Builder()
                .storageEngine(JpaEventStorageEngine.builder()
                        .eventSerializer(serializer)
                        .upcasterChain(s -> s)
                        .dataSource(dataSource)
                        .entityManagerProvider(new SimpleEntityManagerProvider(entityManager))
                        .transactionManager(new SpringTransactionManager(transactionManager))
                        .build())
                .build();
    }

    @Bean
    public SagaStore sagaStore(Serializer serializer, EntityManager entityManager) {
        return JpaSagaStore.builder()
                .serializer(serializer)
                .entityManagerProvider(new SimpleEntityManagerProvider(entityManager))
                .build();
    }

    @Bean
    public TokenStore tokenStore(Serializer serializer, EntityManager entityManager) {
        return new InMemoryTokenStore();
    }

    @Bean
    public CartRepository cartRepository(AxonConfiguration axonConfiguration) {
        return new AxonCartRepository(axonConfiguration.repository(AxonESCartEntity.class));
    }
}
