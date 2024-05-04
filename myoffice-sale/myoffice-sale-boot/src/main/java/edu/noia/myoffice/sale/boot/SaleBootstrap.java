package edu.noia.myoffice.sale.boot;

import edu.noia.myoffice.sale.command.config.SaleCommandConfiguration;
import edu.noia.myoffice.sale.command.data.config.SaleCommandDataConfiguration;
import edu.noia.myoffice.sale.config.DiscoveryConfiguration;
import edu.noia.myoffice.sale.config.SaleDomainConfiguration;
import edu.noia.myoffice.sale.config.SwaggerConfiguration;
import edu.noia.myoffice.sale.event.config.SaleEventConfiguration;
import edu.noia.myoffice.sale.query.config.SaleQueryConfiguration;
import edu.noia.myoffice.sale.query.data.config.SaleQueryDataConfiguration;
import edu.noia.myoffice.sale.rest.config.SaleRestConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(value = {
        //DiscoveryConfiguration.class,
        //SwaggerConfiguration.class,
        SaleDomainConfiguration.class,
        SaleRestConfiguration.class,
        SaleCommandConfiguration.class,
        SaleCommandDataConfiguration.class,
        SaleEventConfiguration.class,
        SaleQueryConfiguration.class,
        SaleQueryDataConfiguration.class
})
@SpringBootApplication
public class SaleBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(SaleBootstrap.class, args);
    }
}
