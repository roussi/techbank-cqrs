package com.aroussi.cqrs.core.annotations;

import org.immutables.value.Value;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Value.Style(
        depluralize = true, // will remove trailing "s", in order to generate addFoo for foos attribute
        depluralizeDictionary = {}, // for special cases, will be in form "singular:plural"
        defaultAsDefault = true,
        optionalAcceptNullable = true,
        overshadowImplementation = true, // do not return immutable implementation, but rather the Abstract Value object (interface)
        visibility = Value.Style.ImplementationVisibility.PACKAGE)
public @interface Builded {
}
