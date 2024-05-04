package edu.noia.myoffice.address.data.rest.adapter.swisspost;

import edu.noia.myoffice.address.data.AddressVerifierClient;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

@Configuration
public class SwissPostAddressVerifierClientConfiguration {

    @Value("${swisspost.ach.client.default-uri}")
    private String defaultUri;

    @Value("${swisspost.ach.client.user.name}")
    private String userName;

    @Value("${swisspost.ach.client.user.password}")
    private String userPassword;

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(getClass().getPackage().getName());
        marshaller.setCheckForXmlRootElement(false);
        return marshaller;
    }

    @Bean
    public WebServiceTemplate webServiceTemplate() {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setMarshaller(marshaller());
        webServiceTemplate.setUnmarshaller(marshaller());
        webServiceTemplate.setDefaultUri(defaultUri);
        webServiceTemplate.setMessageSender(httpComponentsMessageSender());
        return webServiceTemplate;
    }

    @Bean
    public HttpComponentsMessageSender httpComponentsMessageSender() {
        HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
        httpComponentsMessageSender.setCredentials(usernamePasswordCredentials());
        return httpComponentsMessageSender;
    }

    @Bean
    public UsernamePasswordCredentials usernamePasswordCredentials() {
        return new UsernamePasswordCredentials(userName, userPassword);
    }

    @Bean
    public AddressVerifierClient addressVerifier(WebServiceTemplate webServiceTemplate) {
        System.out.println("TEST");
        return new SwissPostAddressVerifierClientAdapter(userName, webServiceTemplate);
    }

}
