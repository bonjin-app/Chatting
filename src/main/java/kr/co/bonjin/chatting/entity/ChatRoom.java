package kr.co.bonjin.chatting.entity;


import kr.co.bonjin.chatting.entity.common.BaseEntity;
import lombok.Getter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Entity
@Table(name = "chat_room")
public class ChatRoom extends BaseEntity {

    // 순번
    @Id
    @Column(name = "chat_room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Room ID
    @Column(name = "room_id")
    private String roomId;

    private String name;

    public ChatRoom() {}

    public static ChatRoom create(String name) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.name = name;
        return chatRoom;
    }

    //==Getter/Setter==//
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
