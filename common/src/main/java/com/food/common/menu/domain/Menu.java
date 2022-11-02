package com.food.common.menu.domain;

import com.food.common.store.domain.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
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

    @Comment("조리 시간 (분 단위)")
    @NotNull
    private Integer cookingTime;

    @OneToMany(mappedBy = "menu")
    private final List<MenuOption> options = new ArrayList<>();

    public static Menu create(Store store, String name, Integer amount, Integer cookingTime) {
        Menu menu = new Menu();
        menu.store = store;
        menu.name = name;
        menu.amount = amount;
        menu.cookingTime = cookingTime;

        return menu;
    }

    public void initOptions(List<MenuOption> options) {
        this.options.addAll(options);
    }

    public Long getStoreId() {
        return store.getId();
    }

    public List<MenuOption> getOptions() {
        return CollectionUtils.isEmpty(options) ? Collections.emptyList() : options;
    }
}
