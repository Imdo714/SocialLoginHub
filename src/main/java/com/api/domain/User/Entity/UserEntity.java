package com.api.domain.User.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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
     */
    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true) // 양방향 관계
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


}
