package com.aroussi.cqrs.core.domain;

import com.aroussi.cqrs.core.events.BaseEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;

import java.lang.reflect.Method;


@Slf4j
public abstract class AggregateRoot {

    @Getter
    protected String id;
    @Getter
    @Setter
    private int version = -1;

    private final MutableList<BaseEvent> changes = Lists.mutable.empty();


    protected MutableList<BaseEvent> getUncommittedChanges() {
        return changes;
    }

    protected void markChangesAsCommitted() {
        changes.clear();
    }

    protected void raiseEvent(BaseEvent event) {
        applyChange(event, true);
    }

    protected void replayChanges(Iterable<BaseEvent> events) {
        events.forEach(event -> applyChange(event, false));
    }

    protected void applyChange(BaseEvent event, boolean isNewEvent) {
        try {
            var applyMethod = this.getClass().getMethod("apply", event.getClass());
            applyMethod.setAccessible(true);
            applyMethod.invoke(this, event);
        } catch (Exception e) {
            log.error("Can't apply change for event {}", event, e);
        } finally {
            if (isNewEvent) changes.add(event);
        }
    }



}
