package com.aroussi.cqrs.techbank.common.events;

import com.aroussi.cqrs.core.annotations.Builded;
import com.aroussi.cqrs.core.events.BaseEvent;

@Builded
public interface AccountClosedEvent extends BaseEvent {

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableAccountClosedEvent.Builder {}
}
