package edu.noia.myoffice.address.domain.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Accessors(chain = true)
@Setter
@Getter
public class PhoneAddress extends Address {

    @NotNull
    String number;

    public PhoneAddress() {
        super(AddressType.PHONE);
    }
}
