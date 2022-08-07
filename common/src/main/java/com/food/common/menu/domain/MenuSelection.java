package com.food.common.menu.domain;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_menu_selection")
@Entity
public class MenuSelection {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "menu_selection_id")
    private Long id;

    @Comment("메뉴 선택옵션")
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "menu_option_id")
    private MenuOption option;

    @Comment("선택지")
    @Length(max = 50)
    @NotNull
    private String selection;

    @Comment("금액")
    @PositiveOrZero
    @NotNull
    private Integer amount;

    public static MenuSelection create(MenuOption option, String selection, Integer amount) {
        MenuSelection menuSelection = new MenuSelection();
        menuSelection.option = option;
        menuSelection.selection = selection;
        menuSelection.amount = amount;

        return menuSelection;
    }
}
