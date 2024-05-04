package edu.noia.myoffice.sale.rest.event;

import edu.noia.myoffice.common.domain.event.DomainEvent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaleEvent<T> implements DomainEvent<T> {

    @NonNull
    Instant timestamp;
    @NonNull
    Class eventClass;
    @NonNull
    T payload;
}
