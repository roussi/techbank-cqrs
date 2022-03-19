package com.aroussi.cqrs.core.domain;

import com.aroussi.cqrs.core.annotation.Builded;
import org.immutables.value.Value;

@Builded
public interface AggregateRoute {
    String getId();

    @Value.Default
    default Integer getVersion() {
        return -1;
    }
}
