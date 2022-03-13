package com.aroussi.cqrs.techbank.query.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {

    @GetMapping("/query")
    Mono<String> sayHello() {
        return Mono.just("Hello world");
    }
}
