package com.aroussi.cqrs.techbank.common.events;

import com.aroussi.cqrs.core.annotations.Builded;
import com.aroussi.cqrs.core.events.BaseEvent;

@Builded
public interface FundsWithdrawnEvent extends BaseEvent {
    Double amount();

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableFundsWithdrawnEvent.Builder {}
}
