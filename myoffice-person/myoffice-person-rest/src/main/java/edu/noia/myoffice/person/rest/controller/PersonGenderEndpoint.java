package edu.noia.myoffice.person.rest.controller;

import edu.noia.myoffice.person.domain.vo.Gender;
import edu.noia.myoffice.person.domain.vo.Person;
import edu.noia.myoffice.person.rest.client.GenderApiClient;
import edu.noia.myoffice.person.rest.dto.GenderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping(path = "/api/person/v1/gender")
public class PersonGenderEndpoint {

    @Autowired
    private GenderApiClient genderApiClient;

    private static Person toPerson(GenderDto dto) {
        return Person.of(
                dto.getFirstName().trim(),
                dto.getLastName().trim(),
                Gender.valueOf(dto.getGender().toUpperCase()), null, null);
    }

    @GetMapping
    public ResponseEntity<Person> split(@RequestParam String fullName) {
        return ok(toPerson(genderApiClient.split(fullName)));
    }
}
