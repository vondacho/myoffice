package edu.noia.myoffice.sale.command.service;

import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.sale.application.command.cart.CloseCartCommand;
import edu.noia.myoffice.sale.application.command.cart.CreateCartCommand;
import edu.noia.myoffice.sale.application.command.cart.OrderCartCommand;
import edu.noia.myoffice.sale.application.command.item.AddItemToCartCommand;
import edu.noia.myoffice.sale.application.command.item.DeposeItemIntoCartCommand;
import edu.noia.myoffice.sale.application.command.item.RemoveItemFromCartCommand;
import edu.noia.myoffice.sale.application.service.CartUseCase;
import edu.noia.myoffice.sale.domain.cart.CartRepository;
import edu.noia.myoffice.sale.domain.cart.CartSample;
import org.axonframework.commandhandling.CommandHandler;

public class AxonCartUseCase extends CartUseCase {

    public AxonCartUseCase(CartRepository cartRepository, EventPublisher eventPublisher) {
        super(cartRepository, eventPublisher);
    }

    @CommandHandler
    @Override
    public void on(CreateCartCommand command) {
        cartRepository.save(null, CartSample
                .builder(command.getFolderId(), command.getType())
                .title(command.getTitle())
                .notes(command.getNotes()).build());
    }

    @CommandHandler
    @Override
    public void on(AddItemToCartCommand command) {
        super.on(command);
    }

    @CommandHandler
    @Override
    public void on(RemoveItemFromCartCommand command) {
        super.on(command);
    }

    @CommandHandler
    @Override
    public void on(DeposeItemIntoCartCommand command) {
        super.on(command);
    }

    @CommandHandler
    @Override
    public void on(OrderCartCommand command) {
        super.on(command);
    }

    @CommandHandler
    @Override
    public void on(CloseCartCommand command) {
        super.on(command);
    }
}
