package kr.co.bonjin.chatting.entity;

import kr.co.bonjin.chatting.entity.common.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User extends BaseEntity {

    // 순번
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "token")
    private String token;

    protected User() {}

    public User(String name, String token) {
        this.name = name;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
