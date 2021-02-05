package kr.co.bonjin.chatting.service;

import kr.co.bonjin.chatting.entity.ChatRoom;
import kr.co.bonjin.chatting.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;

@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    @PostConstruct
    private void init() {
    }

    public Collection<ChatRoom> findAllRoom() {
        return chatRoomRepository.findAll();
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRoomRepository.findById(roomId);
    }

    @Transactional
    public Long save(String name) {
        String randomId = UUID.randomUUID().toString();
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setRoomId(randomId);
        chatRoom.setName(name);
        return chatRoomRepository.save(chatRoom);
    }
}
