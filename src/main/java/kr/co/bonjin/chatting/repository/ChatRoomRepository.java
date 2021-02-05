package kr.co.bonjin.chatting.repository;

import kr.co.bonjin.chatting.entity.ChatRoom;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Repository
public class ChatRoomRepository {

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    private void init() {

    }

    public List<ChatRoom> findAll() {
        List<ChatRoom> rooms = em.createQuery("select cr from ChatRoom cr order by cr.id desc", ChatRoom.class)
                .getResultList();
        return rooms;
    }

    public ChatRoom findById(String roomId) {
        ChatRoom room = em.createQuery("select cr from ChatRoom cr where cr.roomId = :roomId", ChatRoom.class)
                .setParameter("roomId", roomId)
                .getSingleResult();
        return room;
    }

    public Long save(ChatRoom chatRoom) {
        em.persist(chatRoom);
        return chatRoom.getId();
    }
}