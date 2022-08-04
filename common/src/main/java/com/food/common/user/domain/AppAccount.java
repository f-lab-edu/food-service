package com.food.common.user.domain;

import com.food.common.common.domain.BaseTimeEntity;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_app_account")
@Entity
public class AppAccount extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "app_account_id")
    private Long id;

    @NotBlank
    @Email
    @Comment("로그인 아이디")
    private String loginId;

    @NotBlank
    @Comment("비밀번호")
    private String password;

    @NotNull
    @Comment("유저")
    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public static AppAccount create(String loginId, String password, User user) {
        AppAccount appAccount = new AppAccount();
        appAccount.loginId = loginId;
        appAccount.password = password;
        appAccount.user = user;

        return appAccount;
    }

    public Long getId() {
        return id;
    }
}
