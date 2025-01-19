package com.api.domain.User.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 *  @NoArgsConstructor : 매개변수가 없는 기본 생성자를 생성합니다.
 *  @AllArgsConstructor : 모든 필드를 매개변수로 받는 생성자를 생성합니다.
 *  @Builder : 빌더 패턴을 사용할 수 있도록 메서드를 자동 생성합니다.
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name="users")
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
     *
     *  mappedBy는 JPA에서 양방향 관계를 설정할 때 사용하는 속성으로, 관계의 주인을 명시합니다.
     *  주인(Owner): 데이터베이스에서 외래 키를 관리하는 엔티티.
     *  종속자(Inverse): 외래 키의 설정을 주인에게 위임하는 엔티티. mappedBy로 주인을 지정.
     *
     *  orphanRemoval은 부모-자식 관계에서 고아 객체(orphan)를 자동으로 삭제하는 기능입니다.
     */
    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.REMOVE, orphanRemoval = true) // 양방향 관계
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
    private LocalDateTime createDate;


}
