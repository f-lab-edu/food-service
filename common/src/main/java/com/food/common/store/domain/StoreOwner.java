package com.food.common.store.domain;

import com.food.common.common.domain.BaseTimeEntity;
import com.food.common.user.domain.User;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static com.food.common.store.utils.StoreValidationFailureMessages.StoreOwner.USER_CANNOT_BE_NULL;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_store_owner")
@Entity
public class StoreOwner extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "store_owner_id")
    private Long id;

    @Comment("유저")
    @NotNull(message = USER_CANNOT_BE_NULL)
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public static StoreOwner create(User user) {
        StoreOwner storeOwner = new StoreOwner();
        storeOwner.user = user;

        return storeOwner;
    }

    public Long getId() {
        return id;
    }
}
