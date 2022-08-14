package com.zming.cloudnacos5998.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class MyFilter implements Ordered, GlobalFilter {

    /**
     * 可以拿到对应的request 和 response
     *  chain 过滤器链
     * */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return null;
    }

    /**
     * 加载过滤器的顺序
     * return 数数值越小优先级越高级
     * */
    @Override
    public int getOrder() {
        return 0;
    }
}
