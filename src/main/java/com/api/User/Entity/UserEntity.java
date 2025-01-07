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
    @Column(name = "user_id")
    private int userid;

    /**
     *  @OneToOne : 1 : 1 관계    서로 설정
     *  @OneToMany : 1 : N 관계  하나의 부모 여러개의 자식이있으면 부모 엔티티에 설정
     *  @ManyToOne : N : 1 관계  여러개의 자식 하나의 부모가있으면 자식 엔티티에 설정
     *  @ManyToMany : N : M 관계
     */
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private TokenEntity tokenEntity;

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
    public UserEntity(int userid, String username, String email, String nickname, String providerid, String role, String createDate) {
        this.userid = userid;
        this.username = username;
        this.email = email;
        this.nickname = nickname;
        this.providerid = providerid;
        this.role = role;
        this.createDate = createDate;
    }

    public UserEntity() {}

}
