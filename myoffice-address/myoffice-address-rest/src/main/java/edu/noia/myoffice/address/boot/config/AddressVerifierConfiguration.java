package edu.noia.myoffice.address.boot.config;

import edu.noia.myoffice.address.data.rest.adapter.swisspost.SwissPostAddressVerifierClientConfiguration;
import edu.noia.myoffice.address.rest.controller.AddressVerificationEndpoint;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@ComponentScan(basePackageClasses = AddressVerificationEndpoint.class)
@Import(value = SwissPostAddressVerifierClientConfiguration.class)
@Configuration
public class AddressVerifierConfiguration {
}
