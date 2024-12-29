package com.api.User.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Table(name="users", indexes = @Index(name = "idx_nickname", columnList = "nickname"))
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;

    @Column
    private String email;

    @Column
    private String nickname;

    @Column
    private String providerid;

    @Column
    private String role; // ROLE_USER, ROLE_ADMIN

    @Column
    @CreationTimestamp
    private String createDate;

    @Builder
    public UserEntity(int id, String username, String email, String nickname, String providerid, String role, String createDate) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.nickname = nickname;
        this.providerid = providerid;
        this.role = role;
        this.createDate = createDate;
    }

    public UserEntity() {}
}
