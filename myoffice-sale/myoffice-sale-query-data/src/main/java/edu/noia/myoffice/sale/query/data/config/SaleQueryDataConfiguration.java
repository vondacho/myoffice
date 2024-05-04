package edu.noia.myoffice.sale.query.data.config;

import edu.noia.myoffice.sale.query.data.adapter.CartEventHandlerAdapter;
import edu.noia.myoffice.sale.query.data.jpa.JpaCartView;
import edu.noia.myoffice.sale.query.data.jpa.JpaCartViewRepository;
import edu.noia.myoffice.sale.query.handler.CartEventHandler;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@EnableJpaRepositories(basePackageClasses = JpaCartViewRepository.class)
@EntityScan(basePackageClasses = JpaCartView.class)
@Configuration
public class SaleQueryDataConfiguration {

    @Bean
    public CartEventHandler cartEventHandler(JpaCartViewRepository repository) {
        return new CartEventHandlerAdapter(repository);
    }

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {
        return new RepositoryRestConfigurer() {
            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
                config.setBasePath("/api/sale/query/v1/")
                        .withEntityLookup()
                        .forRepository(JpaCartViewRepository.class)
                        .withIdMapping(JpaCartView::getId)
                        .withLookup(JpaCartViewRepository::findById);
            }
        };
    }
}