package com.food.common.user.domain;

import com.food.common.address.domain.Address;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_user_address")
@Entity
public class UserAddress {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_address_id")
    private Long id;

    @Comment("유저")
    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Comment("주소별칭")
    @Length(max = 30)
    private String name;

    @Comment("주소")
    @NotNull
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Comment("직접 입력 상세 주소")
    @Length(max = 150)
    private String addressDetail;

    public static UserAddress create(User user, String name, Address address, String addressDetail) {
        UserAddress userAddress = new UserAddress();
        userAddress.user = user;
        userAddress.name = name;
        userAddress.address = address;
        userAddress.addressDetail = addressDetail;

        return userAddress;
    }

    public Long getId() {
        return id;
    }
}
