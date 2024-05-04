package edu.noia.myoffice.sale.application.service;

import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.common.util.holder.Holder;
import edu.noia.myoffice.sale.application.command.cart.CloseCartCommand;
import edu.noia.myoffice.sale.application.command.cart.CreateCartCommand;
import edu.noia.myoffice.sale.application.command.cart.OrderCartCommand;
import edu.noia.myoffice.sale.application.command.item.AddItemToCartCommand;
import edu.noia.myoffice.sale.application.command.item.DeposeItemIntoCartCommand;
import edu.noia.myoffice.sale.application.command.item.RemoveItemFromCartCommand;
import edu.noia.myoffice.sale.application.handler.CartCommandHandler;
import edu.noia.myoffice.sale.domain.cart.*;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

import static edu.noia.myoffice.common.util.exception.ExceptionSuppliers.notFound;

@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public class CartUseCase implements CartCommandHandler {

    @NonNull
    CartRepository cartRepository;
    @NonNull
    EventPublisher eventPublisher;

    public void on(CreateCartCommand command) {
        CartEntity.create(
                CartSample
                        .builder(command.getFolderId(), command.getType())
                        .title(command.getTitle())
                        .notes(command.getNotes()).build(),
                eventPublisher).save(cartRepository);
    }

    public void on(AddItemToCartCommand command) {
        applyOn(command.getCartId(), cart -> {
            if (cart.getType() == CartType.LOG) {
                eventPublisher.publish(ItemCreated.of(cart.getId(), command.getCartItem()));
            } else {
                cart.addItem(command.getCartItem());
            }
        });
    }

    public void on(DeposeItemIntoCartCommand command) {
        applyOn(command.getCartId(), cart -> cart.addItem(command.getCartItem()));
    }

    public void on(RemoveItemFromCartCommand command) {
        applyOn(command.getCartId(), cart -> cart.removeItem(command.getCartItemId()));
    }

    public void on(OrderCartCommand command) {
        applyOn(command.getCartId(), CartEntity::order);
    }

    public void on(CloseCartCommand command) {
        applyOn(command.getCartId(), cart -> cart.close(command.getInvoiceId()));
    }

    private void applyOn(CartId cartId, Consumer<CartEntity> action) {
        find(cartId).execute(cart -> {
            action.accept(cart);
            cart.publish(eventPublisher);
        });
    }

    private Holder<CartEntity> find(CartId cartId) {
        return cartRepository.findOne(cartId).orElseThrow(notFound(CartEntity.class, cartId));
    }
}
