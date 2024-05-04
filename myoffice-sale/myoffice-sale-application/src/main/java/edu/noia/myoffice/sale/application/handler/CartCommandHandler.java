package edu.noia.myoffice.sale.application.handler;

import edu.noia.myoffice.sale.application.command.item.RemoveItemFromCartCommand;
import edu.noia.myoffice.sale.application.command.cart.CloseCartCommand;
import edu.noia.myoffice.sale.application.command.cart.CreateCartCommand;
import edu.noia.myoffice.sale.application.command.cart.OrderCartCommand;
import edu.noia.myoffice.sale.application.command.item.AddItemToCartCommand;
import edu.noia.myoffice.sale.application.command.item.DeposeItemIntoCartCommand;

public interface CartCommandHandler {

    void on(CreateCartCommand command);

    void on(AddItemToCartCommand command);

    void on(DeposeItemIntoCartCommand command);

    void on(RemoveItemFromCartCommand command);

    void on(OrderCartCommand command);

    void on(CloseCartCommand command);
}
