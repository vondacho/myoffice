package edu.noia.myoffice.person.data.rest.adapter.com.genderapi;

import edu.noia.myoffice.person.rest.client.GenderApiClient;
import edu.noia.myoffice.person.rest.dto.GenderDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class GenderApiClientAdapter implements GenderApiClient {

    @NonNull
    private String apiKey;
    @NonNull
    private String endpointUrl;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public GenderDto split(String fullName) {
        return restTemplate.getForObject(String.format("%s?key=%s&split=%s", endpointUrl, apiKey, fullName), GenderDto.class);
    }
}
