package edu.noia.myoffice.address.domain.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Accessors(chain = true)
@Setter
@Getter
public class PostalAddress extends Address {

    String street;
    String houseNbr;
    @NotNull
    String zip;
    @NotNull
    String city;
    String region;
    @NotNull
    String country;

    public PostalAddress() {
        super(AddressType.POSTAL);
    }
}
