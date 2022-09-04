package com.food.common.user.domain;

import com.food.common.common.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.food.common.user.UserValidationFailureMessages.SocialAccount.*;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_social_account")
@Entity
public class SocialAccount extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "app_account_id")
    private Long id;

    @Comment("로그인 아이디")
    @NotBlank(message = LOGIN_ID_CANNOT_BE_BLANK)
    @Length(max = 50, message = LOGIN_ID_IS_OUT_OF_LENGTH_OF_STRING)
    private String loginId;

    @Comment("유저")
    @NotNull(message = USER_CANNOT_BE_NULL)
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public static SocialAccount create(String loginId, User user) {
        SocialAccount socialAccount = new SocialAccount();
        socialAccount.loginId = loginId;
        socialAccount.user = user;

        return socialAccount;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return user.getId();
    }
}
