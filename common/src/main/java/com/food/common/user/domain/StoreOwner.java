package com.food.common.user.domain;

import com.food.common.common.domain.BaseTimeEntity;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static com.food.common.store.utils.StoreValidationFailureMessage.StoreOwner.NOT_NULL_USER;
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
    @NotNull(message = NOT_NULL_USER)
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
