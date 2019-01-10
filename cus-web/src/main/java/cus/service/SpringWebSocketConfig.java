package cus.service;
import cus.handler.SpringWebSocketHandler;
import cus.interceptor.SpringWebSocketHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;
/**
 * @ClassName: SpringWebSocketConfig
 * @Description: WebSocketConfigurer接口
 * @author: dengyn
 * @date: 2018/11/28 15:18
 */
    @Configuration
    @EnableWebMvc
    @EnableWebSocket
    public class SpringWebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
        public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
            registry.addHandler(webSocketHandler(),"/websocket/socketServer.do").addInterceptors(new SpringWebSocketHandlerInterceptor());
            registry.addHandler(webSocketHandler(), "/sockjs/socketServer.do").addInterceptors(new SpringWebSocketHandlerInterceptor()).withSockJS();
        }

        @Bean
        public TextWebSocketHandler webSocketHandler(){
            return new SpringWebSocketHandler();
        }

    }
