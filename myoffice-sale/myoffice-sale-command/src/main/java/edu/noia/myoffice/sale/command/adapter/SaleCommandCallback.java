package edu.noia.myoffice.sale.command.adapter;

import edu.noia.myoffice.common.application.command.Command;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.CommandResultMessage;

@Slf4j
public class SaleCommandCallback implements CommandCallback<Command, Object> {

    @Override
    public void onResult(CommandMessage<? extends Command> commandMessage, CommandResultMessage<?> commandResultMessage) {
        LOG.debug("Command executed: {}", commandMessage.getCommandName());
    }
}
