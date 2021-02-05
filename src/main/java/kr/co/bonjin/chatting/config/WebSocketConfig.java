package kr.co.bonjin.chatting.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

/**
 * STOMP 는 Simple/Streaming Text Oriented Messaging Protocol 의 약자이며 텍스트 기반의 메시징 프로토콜
 *
 * 본적인 콘셉트를 예로 들면 우체통(topic)이 있으면 집배원(publisher)이 신문을 우체통에 배달하는 액션이 있고,
 * 우체통에 신문이 배달되는 것을 기다렸다가 빼서 보는 구독자(subscriber)의 액션이 있다.
 * 여기서 구독자는 여려 명이 될 수 있다.
 *
 * 채팅방을 생성한다 – pub/sub 구현을 위한 Topic 이 하나 생성된다.
 * 채팅방에 입장한다 – Topic 을 구독한다.
 * 채팅방에서 메시지를 보내고 받는다 – 해당 Topic 으로 메시지를 발송하거나(pub) 메시지를 받는다(sub)
 */
@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final StompHandler stompHandler;

    /**
     * pub/sub 메시징을 구현
     * 메시지를 발행하는 요청의 prefix 는 /pub 로 시작하도록 설정.
     * 메시지를 구독하는 요청의 prefix 는 /sub 로 시작하도록 설정.
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/sub");    //  /sub 로 시작하도록 설정
        registry.setApplicationDestinationPrefixes("/pub"); // /pub 로 시작하도록 설정
    }

    /**
     * stomp websocket 의 연결 endpoint 는 /ws-stomp 로 설정.
     * 서버의 접속 주소는 다음과 같음. ws://localhost:8080/ws-stomp
     * ssl 인증서 적용 후에는 wss://localhost:8080/ws-stomp
     *
     * .withSockJS(); 를 사용하면 SockJS 기반. 붙이지 않으면 pure WebSocket
     * stomp 는 sockJS 와 같이 사용.
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp")
//                .setAllowedOrigins("*")
                .withSockJS();
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(stompHandler);
    }
}
