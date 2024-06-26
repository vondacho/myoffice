package edu.noia.myoffice.sale.domain.order;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@ToString
@EqualsAndHashCode(of = {"id"}, doNotUseGetters = true)
@Getter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderId {
    @NonNull
    UUID id;

    public static OrderId random() {
        return new OrderId(UUID.randomUUID());
    }
}
