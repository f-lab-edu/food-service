package com.food.common.user.domain;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static com.food.common.user.UserValidationFailureMessages.User.NICKNAME_HAS_TO_BE_BETWEEN_LENGTH;
import static com.food.common.user.UserValidationFailureMessages.User.NICKNAME_CANNOT_BLANK;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_user")
@Entity
public class User {
    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Comment("닉네임")
    @NotBlank(message = NICKNAME_CANNOT_BLANK)
    @Length(max = 30, message = NICKNAME_HAS_TO_BE_BETWEEN_LENGTH)
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
