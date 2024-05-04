package edu.noia.myoffice.sale.domain.cart;

import edu.noia.myoffice.common.domain.entity.BaseDomainEntity;
import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.common.domain.vo.Amount;
import edu.noia.myoffice.common.util.holder.Holder;
import edu.noia.myoffice.common.util.validation.BeanValidator;
import edu.noia.myoffice.sale.domain.invoice.InvoiceId;
import edu.noia.myoffice.sale.domain.order.OrderId;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;
import java.util.stream.Collectors;

import static edu.noia.myoffice.common.util.exception.ExceptionSuppliers.itemNotFound;
import static edu.noia.myoffice.common.util.validation.Rule.condition;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PROTECTED)
public class CartEntity extends BaseDomainEntity<CartEntity, CartId, CartState> {

    protected CartEntity(CartId id, CartState state) {
        super(id, state);
    }

    public static CartEntity create(CartState state, EventPublisher eventPublisher) {
        CartSample cartState = CartSample.builder(validate(state)).build();
        CartId cartId = CartId.of(UUID.randomUUID());
        CartEntity cart = new CartEntity(cartId, cartState).andEvent(CartCreated.from(cartId, cartState));
        return cart.publish(eventPublisher);
    }

    protected static <T> T validate(T state) {
        return BeanValidator.validate(state);
    }

    public CartType getType() {
        return state.getType();
    }

    @Override
    protected CartState cloneState() {
        return CartSample.builder(state).build();
    }

    public Amount getTotal() {
        if (state.getItems().isEmpty()) {
            return Amount.ZERO;
        } else {
            Amount total = Amount.from(Amount.ZERO);
            state.getItems().forEach(item -> total.plus(item.getPrice()));
            return total;
        }
    }

    public CartEntity addItem(CartItem cartItem) {
        condition(() -> state.getOrderId().isEmpty(), String.format("Cart %s has been ordered, no more add possible", getId()));
        return andEvent(ItemAddedToCart.of(getId(), validate(cartItem)));
    }

    public CartEntity removeItem(CartItemId itemId) {
        condition(() -> state.getOrderId().isEmpty(), String.format("Cart %s has been ordered, no more add possible", getId()));
        return state.getItem(itemId).map(it -> andEvent(ItemRemovedFromCart.of(getId(), itemId)))
                .orElseThrow(itemNotFound(CartItem.class, itemId, CartEntity.class, getId()));
    }

    public CartEntity order() {
        condition(() -> state.getOrderId().isEmpty(), String.format("Cart %s is already ordered", getId()));
        setState(CartSample.builder(state).orderId(OrderId.random()).build());
        return andEvent(CartOrdered.of(getId(),
                state.getType(),
                state.getFolderId(),
                state.getOrderId().get(),
                getTotal()));
    }

    public CartEntity close(InvoiceId invoiceId) {
        condition(() -> state.getOrderId().isPresent(), String.format("Cart %s has not been ordered, hence not closeable", getId()));
        condition(() -> state.getInvoiceId().isEmpty(), String.format("Cart %s is already closed", getId()));
        setState(CartSample.builder(state).invoiceId(invoiceId).build());
        return andEvent(CartInvoiced.of(getId(), state.getInvoiceId().get()));
    }

    public Holder<CartEntity> save(CartRepository repository) {
        return repository.save(id, state);
    }

    protected void created(CartCreated event) {
        setId(event.getCartId());
        setState(CartSample
                .builder(event.getFolderId(), event.getType())
                .title(event.getTitle())
                .notes(event.getNotes()).build());
    }

    protected void itemAdded(ItemAddedToCart event) {
        setState(CartSample.builder(state).item(event.getCartItem()).build());
    }

    protected void itemRemoved(ItemRemovedFromCart event) {
        setState(CartSample
                .builder(state)
                .items(state.getItems()
                        .stream()
                        .filter(it -> it.getId().equals(event.getCartItemId()))
                        .collect(Collectors.toList()))
                .build());
    }

    protected void ordered(CartOrdered event) {
        setState(CartSample.builder(state).orderId(event.getOrderId()).build());
    }

    protected void invoiced(CartInvoiced event) {
        setState(CartSample.builder(state).invoiceId(event.getInvoiceId()).build());
    }
}
