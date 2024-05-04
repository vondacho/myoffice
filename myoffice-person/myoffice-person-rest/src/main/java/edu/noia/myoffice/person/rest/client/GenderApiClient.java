package edu.noia.myoffice.person.rest.client;

import edu.noia.myoffice.person.rest.dto.GenderDto;

public interface GenderApiClient {

    GenderDto split(String fullName);
}
