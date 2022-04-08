package com.aroussi.cqrs.techbank.cmd.infrastructure;

import com.aroussi.cqrs.core.commands.BaseCommand;
import com.aroussi.cqrs.core.commands.CommandDispatcher;
import com.aroussi.cqrs.core.commands.CommandHandlerMethod;
import com.aroussi.cqrs.techbank.common.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.map.MutableMap;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AccountCommandDispatcher implements CommandDispatcher {

    private final MutableMap<Class<? extends BaseCommand>, CommandHandlerMethod> handlers = Maps.mutable.empty();

    @Override
    public <T extends BaseCommand> void registerCommandHandler(Class<T> tClass, CommandHandlerMethod<T> commandHandlerMethod) {
        handlers.put(tClass, commandHandlerMethod);
    }

    @Override
    public void send(BaseCommand command) {
        var commandClass = command.getClass();
        Optional.ofNullable(handlers.get(commandClass))
                .orElseThrow(() -> new ServiceException("there is no handler for command " + commandClass))
                .handle(command);
    }
}
