package edu.noia.myoffice.sale.command.adapter;

import edu.noia.myoffice.common.application.command.Command;
import edu.noia.myoffice.common.application.command.CommandPublisher;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.axonframework.commandhandling.GenericCommandMessage.asCommandMessage;

@RequiredArgsConstructor
public class CommandPublisherAdapter implements CommandPublisher {

    @NonNull
    CommandGateway commandGateway;
    @NonNull
    CommandCallback saleCommandCallback;

    @Override
    public void publish(Command... commands) {
        Map<String, String> metadata = new HashMap<>();
        Arrays.stream(commands).forEach(command ->
                commandGateway.send(asCommandMessage(command).withMetaData(metadata), saleCommandCallback));
    }
}
