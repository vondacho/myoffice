package edu.noia.myoffice.address.data;

import edu.noia.myoffice.address.domain.vo.Address;

import java.util.Optional;
import java.util.stream.Stream;

public interface AddressVerifierClient {

    Stream<Address> findStreets(String street, String zip, String city, Integer limit);

    Stream<Address> findAddresses(String street, String houseNbr, String zip, String city, Integer limit);

    Optional<Address> validateOccupation(String street, String houseNbr, String zip, String city, String personName);

    enum Mode {
        EXACT, PHONETIC
    }
}
