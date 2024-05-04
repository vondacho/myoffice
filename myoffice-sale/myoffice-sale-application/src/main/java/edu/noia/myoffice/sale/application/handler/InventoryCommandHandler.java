package edu.noia.myoffice.sale.application.handler;

import edu.noia.myoffice.sale.application.command.article.ReserveArticleCommand;

public interface InventoryCommandHandler {

    void on(ReserveArticleCommand command);
}