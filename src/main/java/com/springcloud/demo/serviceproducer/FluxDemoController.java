package com.springcloud.demo.serviceproducer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/demo")
@RestController
public class FluxDemoController {

    @GetMapping("/customer/{customer}")
    public Mono<Account> findByCustomer(@PathVariable(name = "customer") String customer) {
        System.out.println("Customer => " + customer + " [ " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS")) + " ]");
        Account account = new Account();
        account.setAmount(2000d);
        account.setCustomerId(customer);
        account.setId(System.nanoTime()+"");
        return Mono.justOrEmpty(account);
    }

    @GetMapping("/customers")
    public Flux<Account> findListByCustomer() {
        List<Account> accountList = new ArrayList<>();
        int counter = 0;
        while (counter++<10){
            Account account = new Account();
            account.setAmount(2000d);
            account.setCustomerId("用户："+counter);
            account.setId(System.nanoTime()+"");
            accountList.add(account);
        }
        return Flux.fromIterable(accountList);
    }

    @GetMapping("/void")
    public Flux<Void> voidFlux(){
        return Flux.empty();
    }

    @GetMapping("/jsonb")
    public Flux<String> jsonb(){
        Jsonb jsonb = JsonbBuilder.create();
        String str = jsonb.toJson(new User("111","dfd"));
        return Flux.just(str);
    }
}
