package com.aroussi.cqrs.core.commands;

import com.aroussi.cqrs.core.annotations.Builded;
import com.aroussi.cqrs.core.messages.Message;

@Builded
public interface BaseCommand extends Message {

    static Message.Builder build() {
        return new Message.Builder();
    }

    class Builder extends ImmutableBaseCommand.Builder {}
}
