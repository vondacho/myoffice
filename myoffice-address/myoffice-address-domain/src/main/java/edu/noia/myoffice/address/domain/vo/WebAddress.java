package edu.noia.myoffice.address.domain.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Accessors(chain = true)
@Setter
@Getter
public class WebAddress extends Address {

    @NotNull
    String url;

    public WebAddress() {
        super(AddressType.WEB);
    }
}
