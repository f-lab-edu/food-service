package com.food.common.user.domain;

import com.food.common.common.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.food.common.user.UserValidationFailureMessages.AppAccount.*;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_app_account")
@Entity
public class AppAccount extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "app_account_id")
    private Long id;

    @Comment("로그인 아이디")
    @Email(message = LOGIN_ID_HAS_TO_BE_IN_EMAIL_FORMAT)
    @Length(max = 50, message = LOGIN_ID_IS_OUT_OF_LENGTH_OF_STRING)
    private String loginId;

    @Comment("비밀번호")
    @NotBlank(message = PASSWORD_CANNOT_BE_BLANK)
    @Length(max = 100, message = PASSWORD_HAS_TO_BE_BETWEEN_LENGTH)
    private String password;

    @Comment("유저")
    @NotNull(message = USER_CANNOT_BE_NULL)
    @OneToOne
    @JoinColumn(name = "user_id")
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

    public Long getUserId() {
        return user.getId();
    }
}
