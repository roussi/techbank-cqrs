package com.aroussi.cqrs.core.messages;

import com.aroussi.cqrs.core.annotations.Builded;

@Builded
public interface Message {

    String id();

    static Builder build() {
        return new Builder();
    }

    class Builder extends ImmutableMessage.Builder {}
}
