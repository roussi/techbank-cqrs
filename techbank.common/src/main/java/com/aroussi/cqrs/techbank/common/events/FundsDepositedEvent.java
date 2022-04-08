package com.aroussi.cqrs.techbank.common.events;

import com.aroussi.cqrs.core.annotations.Builded;
import com.aroussi.cqrs.core.events.BaseEvent;
import com.aroussi.cqrs.techbank.common.dtos.AccountType;

import java.time.ZonedDateTime;

@Builded
public interface FundsDepositedEvent extends BaseEvent {
    Double amount();

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableFundsDepositedEvent.Builder {}
}
