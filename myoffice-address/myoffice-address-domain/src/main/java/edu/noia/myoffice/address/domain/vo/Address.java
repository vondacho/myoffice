package edu.noia.myoffice.address.domain.vo;

import lombok.*;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public abstract class Address {

    @NonNull
    AddressType type;
    String tags;
}
