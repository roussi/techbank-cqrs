package com.aroussi.cqrs.techbank.cmd.infrastructure;

import com.aroussi.cqrs.core.commands.BaseCommand;
import com.aroussi.cqrs.core.commands.CommandHandlerMethod;
import com.aroussi.cqrs.core.commands.CommandDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
public class AccountCommandDispatcher implements CommandDispatcher {

    private Map<Class<? extends BaseCommand>, List<CommandHandlerMethod>> routes = new HashMap<>();

    @Override
    public <T extends BaseCommand> void registerCommandHandler(Class<T> tClass, CommandHandlerMethod<T> commandHandlerMethod) {
        var commandHandlerMethods = routes.computeIfAbsent(tClass, clazz -> new LinkedList<>());
        commandHandlerMethods.add(commandHandlerMethod);
    }

    @Override
    public void send(BaseCommand command) {
        var commandHandlerMethods = routes.get(command.getClass());
        if(CollectionUtils.isEmpty(commandHandlerMethods)) {
            throw new IllegalStateException("There is no command handler");
        }
        if(commandHandlerMethods.size() > 1) {
           log.error("There is more than one registered command handler");
        }
        commandHandlerMethods.get(0).handle(command);
    }


}
