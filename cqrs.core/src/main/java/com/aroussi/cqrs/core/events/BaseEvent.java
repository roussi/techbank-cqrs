package com.aroussi.cqrs.core.events;

import com.aroussi.cqrs.core.annotations.Builded;
import com.aroussi.cqrs.core.messages.Message;
import org.immutables.value.Value;

@Builded
public interface BaseEvent extends Message {

    @Value.Default
    default int version() {
        return -1;
    }

    static Builder build() {
        return new Builder();
    }

    class Builder extends ImmutableBaseEvent.Builder {}
}
