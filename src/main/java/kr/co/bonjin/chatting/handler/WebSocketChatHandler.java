package kr.co.bonjin.chatting.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.bonjin.chatting.entity.ChatMessage;
import kr.co.bonjin.chatting.entity.ChatRoom;
import kr.co.bonjin.chatting.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSocketChatHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatRoomService chatRoomService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {}", payload);

        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
        ChatRoom room = chatRoomService.findRoomById(chatMessage.getRoomId());
//        room.handleActions(session, chatMessage, chatService);
    }
}
