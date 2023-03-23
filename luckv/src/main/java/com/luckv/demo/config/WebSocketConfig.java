package com.luckv.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


//@Component
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	/*
	 * @Bean public ServerEndpointExporter serverEndpointExporter() {
	 * 
	 * Spring에서 Bean은 싱글톤으로 관리되지만,
	 * 
	 * @ServerEndpoint 클래스는 WebSocket이 생성될 때마다 인스턴스가 생성되고 JWA에 의해 관리되기 때문에
	 * Spring의 @Autowired가 설정된 멤버들이 초기화 되지 않습니다. 연결해주고 초기화해주는 클래스가 필요
	 * 
	 * return new ServerEndpointExporter(); }
	 */
	
	
	@Override
    // 클라이언트가 메시지를 구독할 endpoint를 정의합니다.
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/send");
    }

    @Override
    // connection을 맺을때 CORS 허용합니다.
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/")
    // 스프링 5.3, 스프링부트 2.4 버전 부터 allowCredentials이 true인 경우 setAllowedOrigins 메서드에서 
    // 와일드 카드 `'*'`을 사용하실 수 없습니다.
    //  	.setAllowedOrigins("*")
            .setAllowedOriginPatterns("*")
            .withSockJS();
    }
}
