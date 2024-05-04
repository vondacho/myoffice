package edu.noia.myoffice.sale.command.data.repository;

import edu.noia.myoffice.common.util.holder.Holder;
import edu.noia.myoffice.sale.command.entity.AxonESCartEntity;
import edu.noia.myoffice.sale.domain.cart.CartEntity;
import edu.noia.myoffice.sale.domain.cart.CartState;
import edu.noia.myoffice.sale.domain.cart.CartRepository;
import edu.noia.myoffice.sale.domain.cart.CartId;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.axonframework.modelling.command.Aggregate;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.command.Repository;

import java.util.Optional;
import java.util.function.Consumer;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AxonCartRepository implements CartRepository {

    @NonNull
    Repository<AxonESCartEntity> repository;

    @Override
    public Optional<Holder<CartEntity>> findOne(CartId cartId) {
        try {
            return Optional.of(new CartHolder(repository.load(cartId.toString())));
        } catch (AggregateNotFoundException e) {
            return Optional.empty();
        }
    }

    @Override
    public Holder<CartEntity> save(CartId id, CartState state) {
        try {
            return new CartHolder(repository.newInstance(() -> (AxonESCartEntity)AxonESCartEntity.create(state)));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @RequiredArgsConstructor
    private class CartHolder implements Holder<CartEntity> {
        @NonNull
        Aggregate<AxonESCartEntity> aggregate;

        @Override
        public void execute(Consumer<CartEntity> action) {
            aggregate.execute(action::accept);
        }
    }
}
