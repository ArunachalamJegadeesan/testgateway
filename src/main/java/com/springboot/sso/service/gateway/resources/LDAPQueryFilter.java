package com.springboot.sso.service.gateway.resources;

import java.security.Principal;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class LDAPQueryFilter implements WebFilter {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange,
                             WebFilterChain webFilterChain)
    {

        Mono<Principal> principal = serverWebExchange.getPrincipal();
        exchangePrincipal(serverWebExchange).subscribe(
                value -> logger.info("DINGUKU"+value)
        );



        serverWebExchange.getResponse()
                .getHeaders().add("web-filter", "web-filter-test");
        return webFilterChain.filter(serverWebExchange);
    }

    public Mono<String> exchangePrincipal(ServerWebExchange exchange) {
        return Mono.just("test");
        //return exchange.getPrincipal().map( p ->  p.toString());
    }
};