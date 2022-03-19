package com.aroussi.cqrs.core.commands;

public interface CommandDispatcher {
    <T extends BaseCommand> void registerCommandHandler(Class<T> tClass, CommandHandlerMethod<T> commandHandlerMethod);
    void send(BaseCommand command);
}
