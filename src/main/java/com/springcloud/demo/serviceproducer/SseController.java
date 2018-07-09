package com.springcloud.demo.serviceproducer;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/sse")
public class SseController {

    private int func(){
        return ThreadLocalRandom.current().nextInt();
    }

    @GetMapping("/randomNumbers")
    public Flux<ServerSentEvent<User>> randomNumbers() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(seq -> Tuples.of(seq,func(),new User("666","老王666")))
                .map(data -> ServerSentEvent.<User>builder()
                        .event("random")
                        .id(Long.toString(data.getT1()))
                        .comment(Integer.toString(data.getT2()))
                        .data(data.getT3())
                        .build());
    }
}