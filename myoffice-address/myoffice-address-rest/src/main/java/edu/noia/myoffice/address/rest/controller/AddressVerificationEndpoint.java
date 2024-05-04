package edu.noia.myoffice.address.rest.controller;

import edu.noia.myoffice.address.data.AddressVerifierClient;
import edu.noia.myoffice.address.domain.vo.Address;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping(path = "/api/address/v1")
@RequiredArgsConstructor
public class AddressVerificationEndpoint {

    @NonNull
    private AddressVerifierClient addressVerifierClient;

    @GetMapping("/addresses/occupation")
    public ResponseEntity<Address> validateOccupation(@RequestParam String street,
                                                      @RequestParam(required = false) String houseNbr,
                                                      @RequestParam(required = false) String zip,
                                                      @RequestParam(required = false) String city,
                                                      @RequestParam String personName) {

        return addressVerifierClient.validateOccupation(street, houseNbr, zip, city, personName)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/streets/search")
    public ResponseEntity<List<Address>> findStreets(@RequestParam(required = false) String street,
                                                     @RequestParam(required = false) String zip,
                                                     @RequestParam(required = false) String city,
                                                     @RequestParam(required = false) Integer limit) {
        return ok(addressVerifierClient.findStreets(street, zip, city, limit).collect(toList()));
    }

    @GetMapping("/addresses/search")
    public ResponseEntity<List<Address>> findAddresses(@RequestParam(required = false) String street,
                                                       @RequestParam(required = false) String houseNbr,
                                                       @RequestParam(required = false) String zip,
                                                       @RequestParam(required = false) String city,
                                                       @RequestParam(required = false) Integer limit) {
        return ok(addressVerifierClient.findAddresses(street, houseNbr, zip, city, limit).collect(toList()));
    }
}
