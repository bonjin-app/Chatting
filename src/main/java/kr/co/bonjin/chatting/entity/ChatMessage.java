package kr.co.bonjin.chatting.entity;

import kr.co.bonjin.chatting.entity.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "chat_message")
public class ChatMessage extends BaseEntity {

    // 순번
    @Id
    @Column(name = "chat_message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MessageType type;   // 메시지 타입

    // Room ID
    @Column(name = "room_id")
    private String roomId;  // 방번호

    private String sender;  // 메시지 보낸 사람
    private String message; // 메시지


    // 메시지 타입 : 입장, 채팅
    public enum MessageType {
        ENTER, TALK
    }

    public ChatMessage() {}
}
