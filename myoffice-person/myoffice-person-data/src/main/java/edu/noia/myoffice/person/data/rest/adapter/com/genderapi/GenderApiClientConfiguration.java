package edu.noia.myoffice.person.data.rest.adapter.com.genderapi;

import edu.noia.myoffice.person.rest.client.GenderApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenderApiClientConfiguration {

    @Value("${com.gender-api.key}")
    private String apiKey;

    @Value("${com.gender-api.entpointUrl}")
    private String entpointUrl;

    @Bean
    public GenderApiClient genderApiClient() {
        return new GenderApiClientAdapter(apiKey, entpointUrl);
    }

}
