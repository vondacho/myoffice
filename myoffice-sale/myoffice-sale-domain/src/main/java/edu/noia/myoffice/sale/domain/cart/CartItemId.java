package edu.noia.myoffice.sale.domain.cart;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@ToString
@EqualsAndHashCode(of="id", callSuper = false, doNotUseGetters = true)
@Getter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class CartItemId {
    @NonNull
    UUID id;

    public static CartItemId random() {
        return new CartItemId(UUID.randomUUID());
    }
}
