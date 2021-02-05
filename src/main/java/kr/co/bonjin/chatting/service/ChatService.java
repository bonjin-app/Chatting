package kr.co.bonjin.chatting.service;

import kr.co.bonjin.chatting.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
@Service
public class ChatService {

    private final ChatRepository chatRepository;

    @PostConstruct
    private void init() {
    }
}
