package edu.noia.myoffice.address.data.rest.adapter.swisspost;

import edu.noia.myoffice.address.data.AddressVerifierClient;
import edu.noia.myoffice.address.domain.vo.Address;
import edu.noia.myoffice.address.domain.vo.PostalAddress;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;
import java.math.BigInteger;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
public class SwissPostAddressVerifierClientAdapter extends WebServiceGatewaySupport implements AddressVerifierClient {

    private static final String SWITZERLAND = "Suisse";

    @NonNull
    private String callUser;

    @NonNull
    private WebServiceTemplate webServiceTemplate;

    /**
     * Search type 3
     * Mandatory parameters: at least one of {street, zip, city}
     * Generic chars allowed
     */
    @Override
    public Stream<Address> findStreets(String street, String zip, String city, Integer limit) {
        return verified(verify(findStreetRequest(street, zip, city, limit)), value -> true);
    }

    /**
     * Search type 2
     * Mandatory parameters: at least two of {street, houseNbr, zip, city}
     */
    @Override
    public Stream<Address> findAddresses(String street, String houseNbr, String zip, String city, Integer limit) {
        return verified(verify(findAddressRequest(street, houseNbr, zip, city, limit)), value -> true);
    }

    /**
     * Search type 1
     * Mandatory parameters: all of {personName, street, zip or city}
     */
    @Override
    public Optional<Address> validateOccupation(String street, String houseNbr, String zip, String city, String personName) {
        return verified(verify(validatePersonRequest(street, houseNbr, zip, city, personName)),
                row -> BigInteger.ONE.equals(row.getMatchUniqueness())).findFirst();
    }

    @SuppressWarnings("unchecked")
    private AdressCheckerResponseType verify(AdressCheckerRequestType request) {
        JAXBElement<AdressCheckerResponseType> element = (JAXBElement<AdressCheckerResponseType>) webServiceTemplate
                .marshalSendAndReceive(new ObjectFactory().createAdressCheckerRequest(request));
        return element.getValue();
    }

    private Stream<Address> verified(AdressCheckerResponseType response, Predicate<AdressCheckerResponseType.Rows> rowFilter) {
        return response
                .getRows()
                .stream()
                .filter(rowFilter)
                .map(row -> new PostalAddress()
                        .setStreet(row.getStreetFormatted())
                        .setHouseNbr(row.getHouseNbr())
                        .setZip(row.getZip())
                        .setCity(row.getTown27())
                        .setCountry(SWITZERLAND));
    }

    private AdressCheckerRequestType findStreetRequest(String street, String zip, String city, Integer limit) {
        AdressCheckerRequestType request = new ObjectFactory().createAdressCheckerRequestType();
        request.setParams(prepareParams(BigInteger.valueOf(3), limit));
        request.setStreet(street);
        request.setZip(zip);
        request.setTown(city);
        request.setHouseKey(BigInteger.ZERO);
        return request;
    }

    private AdressCheckerRequestType findAddressRequest(String street, String houseNbr, String zip, String city, Integer limit) {
        AdressCheckerRequestType request = new ObjectFactory().createAdressCheckerRequestType();
        request.setParams(prepareParams(BigInteger.valueOf(2), limit));
        request.setStreet(street);
        request.setHouseNbr(houseNbr);
        request.setZip(zip);
        request.setTown(city);
        request.setHouseKey(BigInteger.ZERO);
        return request;
    }

    private AdressCheckerRequestType validatePersonRequest(String street, String houseNbr, String zip, String city, String personName) {
        AdressCheckerRequestType request = new ObjectFactory().createAdressCheckerRequestType();
        request.setParams(prepareParams(BigInteger.ONE, null));
        request.setStreet(street);
        request.setHouseNbr(houseNbr);
        request.setZip(zip);
        request.setTown(city);
        request.setNames(personName);
        return request;
    }

    private AdressCheckerRequestType.Params prepareParams(BigInteger searchType, Integer limit) {
        AdressCheckerRequestType.Params params = new AdressCheckerRequestType.Params();
        params.setSearchType(searchType);
        params.setSearchLanguage(BigInteger.valueOf(2));
        params.setMaxRows(limit != null ? BigInteger.valueOf(limit) : BigInteger.TEN);
        params.setCallUser(callUser);
        return params;
    }
}
