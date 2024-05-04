package edu.noia.myoffice.sale.application.service;

import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.sale.application.command.article.ReserveArticleCommand;
import edu.noia.myoffice.sale.domain.article.SystemReservedArticle;
import edu.noia.myoffice.sale.application.handler.InventoryCommandHandler;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InventoryUseCase implements InventoryCommandHandler {

    @NonNull
    EventPublisher eventPublisher;

    @Override
    public void on(ReserveArticleCommand command) {
        eventPublisher.publish(SystemReservedArticle.of(
                command.getCartId(), command.getArticleId(), command.getQuantity()));
    }
}
