package edu.noia.myoffice.sale.application.command.cart;

import edu.noia.myoffice.common.application.command.Command;
import edu.noia.myoffice.sale.domain.cart.CartId;

public interface CartCommand extends Command {

    CartId getCartId();
}
