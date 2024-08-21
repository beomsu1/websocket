package bs.websocket.sample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // 메시지 브로커 활성화
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .withSockJS(); // /ws 경로에 WebSocket 엔드포인트 등록
    }

    // 클라이언트는 /ws 엔드포인트를 통해 WebSocket 연결 설정
    // 서버에서 클라이언트로 메시지를 보낼 때는 "/topic"으로 시작하는 주제 사용
    // 클라이언트에서 서버로 메시지를 보낼 때는 "/app"으로 시작하는 목적를 사용
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }
}
