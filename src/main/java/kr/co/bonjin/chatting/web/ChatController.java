package kr.co.bonjin.chatting.web;

import kr.co.bonjin.chatting.entity.ChatMessage;
import kr.co.bonjin.chatting.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final SimpMessageSendingOperations messageSendingOperations;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * @MessageMapping을 통해 Websocket 으로 들어오는 메시지 발행을 처리
     * 클라이언트에서는 prefix 를 붙여서 /pub/chat/message 로 발행 요청을 하면 Controller 가 해당 메시지를 받아 처리
     *
     * 메시지가 발행되면 /sub/chat/room/{roomId}로 메시지를 send 하는 것을 볼 수 있는데 클라이언트에서는 해당 주소를(/sub/chat/room/{roomId}) 구독(subscribe)하고 있다가 메시지가 전달되면 화면에 출력하면 된다
     * 여기서 /sub/chat/room/{roomId}는 채팅룸을 구분하는 값이므로 pub/sub 에서 Topic 의 역할이라고 보면 된다
     * @param message
     */
    @MessageMapping("/chat/message")
    public void message(ChatMessage message, @Header("token") String token) {
        String nickname = jwtTokenProvider.getUserNameFromJwt(token);
        // 로그인 회원 정보로 대화명 설정
        message.setSender(nickname);
        // 채팅방 입장시에는 대화명과 메시지를 자동으로 세팅한다.
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.setSender("[알림]");
            message.setMessage(nickname + "님이 입장하셨습니다.");
        }
        messageSendingOperations.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}

