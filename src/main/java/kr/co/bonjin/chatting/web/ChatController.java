package kr.co.bonjin.chatting.web;

import kr.co.bonjin.chatting.dto.ChatRoom;
import kr.co.bonjin.chatting.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @GetMapping
    public Collection<ChatRoom> findAllRoom() {
        return chatService.findAllRoom();
    }

    @PostMapping
    public ChatRoom createRoom(@RequestParam String name) {
        ChatRoom room = chatService.createRoom(name);
        return room;
    }
}
