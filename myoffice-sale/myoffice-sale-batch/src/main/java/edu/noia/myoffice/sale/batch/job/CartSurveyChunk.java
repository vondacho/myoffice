package edu.noia.myoffice.sale.batch.job;

import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.sale.domain.cart.CartCanBeRequested;
import edu.noia.myoffice.sale.domain.cart.CartId;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class CartSurveyChunk {

    @NonNull
    EventPublisher eventPublisher;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Optional<Pageable> execute(Pageable pageable) {
        return Optional.empty();
    }

    private void checkRequestableCart(CartId cartId) {
        eventPublisher.publish(CartCanBeRequested.of(cartId));
    }
}
