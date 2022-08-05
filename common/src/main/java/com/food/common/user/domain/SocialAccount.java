package com.food.common.user.domain;

import com.food.common.common.domain.BaseTimeEntity;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_social_account")
@Entity
public class SocialAccount extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "app_account_id")
    private Long id;

    @NotBlank
    @Comment("로그인 아이디")
    private String loginId;

    @NotNull
    @Comment("유저")
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
}
