package com.food.common.user.domain;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_user")
@Entity
public class User {
    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotBlank
    @Comment("닉네임")
    private String nickname;

    public static User create(String nickname) {
        User user = new User();
        user.nickname = nickname;

        return user;
    }

    public Long getId() {
        return id;
    }
}
