package edu.noia.myoffice.sale.domain.invoice;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@ToString
@EqualsAndHashCode(of = {"id"}, doNotUseGetters = true)
@Getter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceId {
    @NonNull
    UUID id;
}
