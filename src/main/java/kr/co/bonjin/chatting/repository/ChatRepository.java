package kr.co.bonjin.chatting.repository;

import kr.co.bonjin.chatting.entity.ChatMessage;
import kr.co.bonjin.chatting.entity.ChatRoom;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ChatRepository {

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    private void init() {

    }

    public List<ChatMessage> findAll() {
        List<ChatMessage> messages = em.createQuery(
                "select cm from ChatMessage cm order by cm.id desc", ChatMessage.class)
                .getResultList();
        return messages;
    }

    public ChatMessage findById(String roomId) {
        ChatMessage message = em.createQuery(
                "select cr from ChatRoom cr where cr.roomId = :roomId", ChatMessage.class)
                .setParameter("roomId", roomId)
                .getSingleResult();
        return message;
    }

    public Long save(ChatMessage chatMessage) {
        em.persist(chatMessage);
        return chatMessage.getId();
    }
}