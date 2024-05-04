package edu.noia.myoffice.sale.command.entity;

import edu.noia.myoffice.common.util.holder.Holder;
import edu.noia.myoffice.sale.domain.cart.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Aggregate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AxonESCartEntity extends CartEntity {

    @AggregateIdentifier
    CartId cartAggregateId;

    private AxonESCartEntity(CartId id, CartState state) {
        super(id, state);
        cartAggregateId = id;
    }

    public static CartEntity create(CartState state) {
        CartSample cartState = CartSample.builder(validate(state)).build();
        CartId cartId = CartId.of(UUID.randomUUID());
        CartEntity cart = new AxonESCartEntity(cartId, cartState).andEvent(CartCreated.from(cartId, cartState));
        return cart.publish(AggregateLifecycle::apply);
    }

    @EventSourcingHandler
    @Override
    public void created(CartCreated event) {
        cartAggregateId = event.getCartId();
        super.created(event);
    }

    @EventSourcingHandler
    @Override
    public void itemAdded(ItemAddedToCart event) {
        super.itemAdded(event);
    }

    @EventSourcingHandler
    @Override
    public void itemRemoved(ItemRemovedFromCart event) {
        super.itemRemoved(event);
    }

    @EventSourcingHandler
    @Override
    public void ordered(CartOrdered event) {
        super.ordered(event);
    }

    @EventSourcingHandler
    @Override
    public void invoiced(CartInvoiced event) {
        super.invoiced(event);
    }

    @Override
    public Holder<CartEntity> save(CartRepository repository) {
        return action -> action.accept(this);
    }
}
