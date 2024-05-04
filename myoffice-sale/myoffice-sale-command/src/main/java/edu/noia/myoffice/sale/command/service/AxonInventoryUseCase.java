package edu.noia.myoffice.sale.command.service;

import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.sale.application.command.article.ReserveArticleCommand;
import edu.noia.myoffice.sale.application.service.InventoryUseCase;
import lombok.NonNull;
import org.axonframework.commandhandling.CommandHandler;

public class AxonInventoryUseCase extends InventoryUseCase {

    public AxonInventoryUseCase(@NonNull EventPublisher eventPublisher) {
        super(eventPublisher);
    }

    @CommandHandler
    @Override
    public void on(ReserveArticleCommand command) {
        super.on(command);
    }
}
