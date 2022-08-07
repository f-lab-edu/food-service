package com.food.common.menu.domain;

import com.food.common.store.domain.Store;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import java.time.LocalTime;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_menu")
@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    @Comment("가게")
    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Comment("메뉴명")
    @NotBlank
    @Length(max = 50)
    private String name;

    @Comment("메뉴 금액")
    @PositiveOrZero
    @NotNull
    private Integer amount;

    @Comment("조리 시간")
    @NotNull
    private LocalTime cookingTime;

    public static Menu create(Store store, String name, Integer amount, LocalTime cookingTime) {
        Menu menu = new Menu();
        menu.store = store;
        menu.name = name;
        menu.amount = amount;
        menu.cookingTime = cookingTime;

        return menu;
    }
}
